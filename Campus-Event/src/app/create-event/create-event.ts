import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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
    host: { id: 4}
  };

  constructor(private http: HttpClient) {}

  addevent() {
    // Basic validation check (though done by [disabled] in HTML)
    if (!this.eventData.title || !this.eventData.location) {
      console.error('Please fill in required fields.');
      return;
    }
    
    // The date format must be correct for Spring Boot to parse it as LocalDateTime
    const eventPayload = {
        ...this.eventData,
        // Ensure date is sent as a valid ISO string
        eventDate: this.eventData.eventDate ? new Date(this.eventData.eventDate).toISOString() : null
    };


    this.http.post('http://localhost:8080/api/postevent', eventPayload).subscribe({
      next: (res) => {
        console.log('Event submitted successfully:', res);
        // Optionally, reset the form here:
        this.eventData = { title: '', location: '', eventDate: '', host: {id: 4} }; 
      },
      error: (err) => {
        console.error('Submission failed:', err);
      }
    });
  }

  






}