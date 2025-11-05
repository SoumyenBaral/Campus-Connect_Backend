import { Component, OnInit } from '@angular/core';
import { Footer } from "../footer/footer";
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


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
    receiveUpdates: false,
    student: { id: null as number | null },  // Set dynamically from logged-in user
    event: { id: null as number | null }
  };

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.loadAvailableEvents();
    this.setCurrentUser();  // Assuming you have auth service to get current user ID
  }

  loadAvailableEvents() {
    this.http.get<any[]>('http://localhost:8080/api/available-events').subscribe({
      next: (events) => {
        this.availableEvents = events; //update the event in an array
      },
      error: (err) => console.error('Error loading events:', err)
    });
  }

  setCurrentUser() {
    // Replace with your auth service logic to get current student ID
    const currentUserId = this.getCurrentUserId();
    if (currentUserId) {
      this.registrationData.student.id = currentUserId;
    }
  }

  submitRegistration() {
    if (!this.registrationData.event.id || !this.registrationData.student.id) {
      alert('Please select an event and ensure you are logged in as a student.');
      return;
    }

    const payload = {
      ...this.registrationData,
      mobileNumber: Number(this.registrationData.mobileNumber)  // Ensure number type
    };
    this.http.post('http://localhost:8080/api/postregistration', payload, { responseType: 'text' }).subscribe({
      next: (res) => {
        alert(res);
        // Reset form or redirect
        this.registrationData = { ...this.registrationData, fullName: '', emailAddress: '', mobileNumber: '', organization: '', event: { id: null } };
      },
      error: (err) => {
        alert('Registration failed: ' + (err.error || err.message));
      }
    });
  }


  private getCurrentUserId(): number | null {
    // Implement based on your auth service (e.g., from token or localStorage)
    return 1;  // Placeholder
  }

}

