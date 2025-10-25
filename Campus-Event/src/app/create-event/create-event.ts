import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-event',
  imports: [FormsModule,CommonModule],
  templateUrl: './create-event.html',
  styleUrl: './create-event.css'
})
export class CreateEvent {


constructor(private http: HttpClient) {
    // this.getdata();
  }

eventData = {
    "title": "",
    "location": "",
    "eventDate": ""
  }
// const loggedInUserId = localStorage.getItem('userId');
// this.eventData['host'] = { id: Number(loggedInUserId) };

 addevent() {
//   this.eventData['status'] = 'UPCOMING'; // or any valid EventStatus
// this.eventData[host] = { id: loggedInUserId }; // replace with actual user ID
    this.http.post("http://localhost:8080/api/postevent", this.eventData).subscribe({
      next: (res) => console.log('Event submitted:', res),
      error: (err) => console.error('Submission failed:', err)
    });
  }


}
