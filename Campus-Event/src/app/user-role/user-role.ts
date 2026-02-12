import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Feedback } from '../feedback/feedback';
import { Navbar } from '../navbar/navbar';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-role',
  imports: [Footer, Feedback, RouterLink, RouterOutlet, Navbar],
  templateUrl: './user-role.html',
  styleUrl: './user-role.css'
})
export class UserRole {
   counts: any = {};

  constructor(private http: HttpClient) { }
  ngOnInit() {
    this.fetchCounts();
  }
  fetchCounts() {
    this.http.get<any[]>('http://localhost:8080/api/counts').subscribe({
      next: (data) => {
        this.counts = data;
      },
      error: (err) => {
        console.error('Error fetching counts:', err);
        // Fallback to defaults if needed
        this.counts = { hostCount: 0, coordinatorCount: 0, usersCount: 0, eventCount: 0 };
      }
    });
}
}
