import { Component, VERSION } from '@angular/core';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
@Component({
  selector: 'app-feedback',
  imports: [NgbRatingModule,HttpClientModule],
  templateUrl: './feedback.html',
  styleUrl: './feedback.css'
})
export class Feedback{
title = "Event-Rating";
name = "Give Event Rating";
// currentRate= 0;

// State variables
  readonly initialRate: number = 0; 
  currentRate: number = this.initialRate; 
  isLoading: boolean = false; 
  
  // Hardcoded User ID (Replace with actual user login logic!)
  private currentUserId: number = 1; 
  
  // IMPORTANT: Set the correct base URL for your Spring Boot application
  private apiUrl = 'http://localhost:8080/api/postfeedback'; 

  // Inject HttpClient
  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  handleSubmit(): void {
    if (this.currentRate === 0) {
      alert('Please select a rating before submitting.');
      return;
    }
    
    this.isLoading = true;

    // Data payload
    const payload = {
      starRating: this.currentRate,
      userId: this.currentUserId 
    };
    
    // 🌟 Simplified HTTP POST Request 🌟
    this.http.post(this.apiUrl, payload)
      .subscribe({
        // Successful response
        next: (response) => {
          alert('Feedback submitted successfully! Thank you. 😊');
          
          // 🚀 THE FIX: Reset the component state 🚀
          this.currentRate = this.initialRate; 
          
          this.isLoading = false;
        },
        // Error response (e.g., 4xx, 5xx status codes, or network error)
        error: (err) => {
          console.error('Submission failed:', err);
          alert('Failed to submit feedback. Please try again.');
          this.isLoading = false;
        }
      });
  }
}
