import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  // Base API URL for your Spring Boot application
  private apiUrl = 'http://localhost:8080/api'; 
  private feedbackUrl = `${this.apiUrl}/postfeedback`;

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }
  
  submitFeedback(starRating: number, userId: number | string): Observable<string> {
    const feedbackData = {
      user: { id: userId }, 
      starRating: starRating 
    };
    
    return this.http.post( 
      this.feedbackUrl, 
      feedbackData, 
      { 
        ...this.httpOptions, 
        responseType: 'text' as 'text' 
      }
    ) as Observable<string>;
  }

// <<< NEW METHOD TO FETCH ALL FEEDBACKS
  getAllFeedbacks(): Observable<any[]> {
    const url = `${this.apiUrl}/getfeedback`;
    // Assuming the backend returns an array of JSON objects (Feedbacks)
    return this.http.get<any[]>(url); 
  }

}
