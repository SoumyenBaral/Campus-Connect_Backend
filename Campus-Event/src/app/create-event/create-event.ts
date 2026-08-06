import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [FormsModule, CommonModule,RouterOutlet,Navbar,Footer],
  templateUrl: './create-event.html',
  styleUrls: ['./create-event.css']
})
export class CreateEvent {


  categories = ['MUSIC', 'DANCE', 'TECH'];


  eventData = {
    title: '',
    location: '',
    eventDate: '',
    category: this.categories[0], // Initialize with a default value
    host: { id: null as number| null}
  };

  submissionMessage: string | null = null; // For user feedback

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {

    const currentUserId = this.getCurrentUserId();  // Placeholder function
    if (currentUserId) {
      this.eventData.host.id = currentUserId;
    } else {
      this.submissionMessage = 'Error: No logged-in user found. Please log in.';
    this.router.navigate(['/login']);
      // Optionally: this.router.navigate(['/login']);
    }
  }

  addevent() {
    this.submissionMessage = null; // Clear previous messages
    if (!this.eventData.title || !this.eventData.location || !this.eventData.eventDate || !this.eventData.host.id || !this.eventData.category) {
      this.submissionMessage = 'Error: Please fill in all required fields (Title, Date, Category, Location) and ensure host ID is set.';
      console.error('Validation failed: Missing required field.');
      return; // <--- CRITICAL: Stops execution and prevents the HTTP request
    }
    
    // The date format must be correct for Spring Boot to parse it as LocalDateTime
    const eventPayload = {
        
        title: this.eventData.title,
        location: this.eventData.location,
        eventDate: this.eventData.eventDate,
        category: this.eventData.category,
        host: this.eventData.host
    };


    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/json' // Explicitly tell the server we are sending JSON
    // });

    this.http.post('http://localhost:8080/api/postevent', eventPayload, { responseType: 'text' }).subscribe({
      next: (res: string) => {
        console.log('Event submitted successfully:', res);
        this.submissionMessage = `Event successfully created! ${res}`;
        
        // Reset the form data after success
        const hostId = this.eventData.host.id;
        this.eventData = { title: '', location: '', eventDate: '', category: this.categories[0], host: { id: hostId } }; 
        
        // Navigate to event list
        setTimeout(() => {
          this.router.navigate(['/allevents']);
        }, 1500);
      },
      error: (err) => {
        console.error('Submission failed:', err);
        // Display the error message from the server if available
        const serverError = typeof err.error === 'string' ? err.error : 'Failed to create event. Check server logs.';
        this.submissionMessage = `Error: ${serverError}`;
      }
    });
  }
 
private getCurrentUserId(): number | null {
  const userJson = localStorage.getItem('currentUser');
  if (userJson) {
    try {
      const user = JSON.parse(userJson);
      return user.id || null;  // Return the ID field from stored user object
    } catch (err) {
      console.error('Error parsing user from storage', err);
      return null;
    }
  }
  return null;
}

}