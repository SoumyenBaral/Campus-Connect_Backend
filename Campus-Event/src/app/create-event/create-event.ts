import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './create-event.html',
  styleUrls: ['./create-event.css']
})
export class CreateEvent {
  eventData = {
    title: '',
    location: '',
    eventDate: '',
    host: { id: null as number| null}
  };

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    // Fetch current logged-in user's ID dynamically
    // Replace this with your actual auth service logic (e.g., from token or AuthService)
    const currentUserId = this.getCurrentUserId();  // Placeholder function
    if (currentUserId) {
      this.eventData.host.id = currentUserId;
    } else {
      console.error('No logged-in user found. Redirect to login?');
      // Optionally: this.router.navigate(['/login']);
    }
  }

  addevent() {
    // Basic validation check (though done by [disabled] in HTML)
    if (!this.eventData.title || !this.eventData.location || !this.eventData.eventDate) {
      console.error('Please fill in required fields.');
      return;
    }
    
    // The date format must be correct for Spring Boot to parse it as LocalDateTime
    const eventPayload = {
        ...this.eventData,
        // Ensure date is sent as a valid ISO string
        eventDate: this.eventData.eventDate ? new Date(this.eventData.eventDate).toISOString() : null
    };


    this.http.post('http://localhost:8080/api/postevent', eventPayload,{responseType:'text'}).subscribe({
      next: (res: string) => {
        console.log('Event submitted successfully:', res);
        alert(res);
        // Optionally, reset the form here:
        this.eventData = { title: '', location: '', eventDate: '', host: {id:this.eventData.host.id} }; 
        this.router.navigate(['/Upcoming-Events']);
      },
      error: (err) => {
        console.error('Submission failed:', err);
        alert('Error: '+(err.error || 'Failed to create event. Please try again.'));
      }
    });
  }
  private getCurrentUserId(): number | null{
    return 4;
  }
}