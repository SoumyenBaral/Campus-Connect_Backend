import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-details',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './event-details.html',
  styleUrl: './event-details.css'
})
export class EventDetails implements OnInit {
events: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.fetchEvents();
  }

  fetchEvents() {
    this.http.get<any[]>('http://localhost:8080/api/getAllevents').subscribe({
      next: (data) => {
        this.events = data;
      },
      error: (err) => console.error('Error fetching events:', err)
    });
  }
}
