import { Component, OnInit } from '@angular/core';
import { Footer } from "../footer/footer";
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';



interface UserDetail{
  id: number;
  name: string;
  email:string;
  contact: string;
}

@Component({
  selector: 'app-register-page',
  imports: [Footer, FormsModule, CommonModule],
  templateUrl: './register-page.html',
  styleUrl: './register-page.css'
})
export class RegisterPage implements OnInit {

  availableEvents: any[] = [];

  registrationData = {
    fullName: '',
    emailAddress: '',
    mobileNumber: '',
    organization: '',
    agreeToTerms: false,
    // receiveUpdates: false,agreeToTerms: false,
    // receiveUpdates: false,
    student: { id: null as number | null },  // Set dynamically from logged-in user
    event: { id: null as number | null }
  };

  loggedInStudentName: String = 'Loading...';

  constructor(private http: HttpClient, private router: Router,private route: ActivatedRoute) { }

  ngOnInit() {
    this.loadAvailableEvents();
    this.setCurrentUser();  // Assuming you have auth service to get current user ID
     this.route.queryParams.subscribe(params => {
      const eventId = params['eventId'];
      if (eventId) {
        this.registrationData.event.id = +eventId; // Convert to number
      }
    });
  }

  loadAvailableEvents() {
    this.http.get<any[]>('http://localhost:8080/api/available-events').subscribe({
      next: (events) => {
        this.availableEvents = events;
      },
      error: (err) => {
        console.error('Error loading events:', err);
        alert('Failed to load available events. Please try again.');
      }
    });
  }

  setCurrentUser() {
    // Replace with your auth service logic to get current student ID
    const currentUserId = this.getCurrentUserId();
    if (currentUserId) {
      this.registrationData.student.id = currentUserId;
      this.loadStudentDetails(currentUserId);
    }else{
        alert('You must be logged in as a student to register.');
        this.router.navigate(['/login']); // Redirect if not logged in
    }
  }

  loadStudentDetails(userId: number): void {
    this.http.get<UserDetail>(`http://localhost:8080/api/getuser/${userId}`).subscribe({
        next: (user) => {
            this.loggedInStudentName = user.name;
            this.registrationData.fullName = user.name;
        this.registrationData.emailAddress = user.email;
        this.registrationData.mobileNumber = user.contact;
        },
        error: (err) => {
            console.error('Error fetching student details:', err);
            this.loggedInStudentName = 'User Details Missing'; 
            alert('Failed to load your details. Please log in again.');
            this.router.navigate(['/login']);
        }
    });
  }

  submitRegistration() {
    if (!this.registrationData.event.id || !this.registrationData.student.id) {
      alert('Please select an event and ensure you are logged in as a student.');
      return;
    }

    const payload = {
      fullName: this.registrationData.fullName,
      emailAddress: this.registrationData.emailAddress,
      mobileNumber: this.registrationData.mobileNumber,
      organization: this.registrationData.organization,
      student: this.registrationData.student,
      event: this.registrationData.event
    };


    
    this.http.post('http://localhost:8080/api/postregistration', payload, { responseType: 'text' }).subscribe({
      next: (res) => {
        alert(res);        
        this.registrationData.organization = '';
        this.registrationData.event.id = null;
      },
      error: (err) => {
        console.error('Registration error:', err);
        alert('Registration failed: ' + (err.error || err.message));
      }
    });
  }


  private getCurrentUserId(): number | null {
    // 1. Get the user data string from localStorage. 
    // This key must match the key used during login (e.g., 'currentUser').
    const userString = localStorage.getItem('currentUser'); 

    if (userString) {
      try {
        // 2. Parse the JSON string back into a JavaScript object.
        const user = JSON.parse(userString);
        
        // 3. Ensure the user object has an 'id' property and the role is 'STUDENT'
        // This is a crucial validation step.
        if (user && user.id && user.role === 'STUDENT') {
          // Assuming 'id' is a number
          return user.id; 
        }
      } catch (error) {
        console.error('Error parsing user data from localStorage:', error);
        // Clear invalid storage data
        localStorage.removeItem('currentUser');
      }
    }
    
    // Return null if no data is found, data is invalid, or user is not a STUDENT
    return null;
  }

}

