import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Footer } from '../footer/footer';
import { Feedback } from '../feedback/feedback';
import { Navbar } from "../navbar/navbar";
import { UserGuide } from '../user-guide/user-guide';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-student',
  imports: [Footer, Feedback, RouterLink, RouterOutlet, Navbar,UserGuide],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student {
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
        this.counts = { hostCount: 0, coordinatorCount: 0, studentCount: 0, eventCount: 0 };
      }
    });
}
}