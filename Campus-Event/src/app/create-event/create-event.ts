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
  // eventData = {
  //   title: '',
  //   location: '',
  //   eventDate: '',
  //   host: { id: 4}
  // };

  constructor(private http: HttpClient) {

  }





// eventData={
//   "title":
// }

  // addevent() {
  //   this.http.post('http://localhost:8080/api/postevent', this.eventData).subscribe({
  //     next: (res) => console.log('Event submitted:', res),
  //     error: (err) => console.error('Submission failed:', err)
  //   });
  // }
}