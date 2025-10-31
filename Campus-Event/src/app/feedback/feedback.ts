import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from '../services/auth.service'; 
import { FeedbackService } from '../feedback.service';


@Component({
  selector: 'app-feedback',
  imports: [NgbRatingModule, HttpClientModule], 
  templateUrl: './feedback.html',
  styleUrl: './feedback.css'
})
export class Feedback {
    title = "Event-Rating";
    name = "Give Event Rating";
    isLoading: boolean = false;
    currentRate: number = 0; 

    constructor(
      private authService: AuthService, 
      private feedbackService: FeedbackService 
    ) {}

    
    handleSubmit() {
      if (this.currentRate === 0) return;

      // 1. Get current user ID from AuthService
      const currentUser = (this.authService as any).getCurrentUser(); 
      

      if (!currentUser || !currentUser.id) {
        console.error("Authentication error: User ID is missing. Please log in.");
        alert("Please log in to submit feedback.");
        return;
      }
      
      this.isLoading = true;
      
      // 2. Call the FeedbackService
      this.feedbackService.submitFeedback(this.currentRate, currentUser.id).subscribe({
        next: (response) => {
          console.log('Feedback submitted:', response);
          alert("Thank you for your feedback!");
        },
        error: (error) => {
          console.error('Submission failed:', error);
          alert("Failed to submit feedback. Please try again.");
        },
        complete: () => {
          this.isLoading = false;
          this.currentRate = 0; 
        }
      });
    }

}