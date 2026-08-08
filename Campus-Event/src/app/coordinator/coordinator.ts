import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { CoordinatorGuide } from '../coordinator-guide/coordinator-guide';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-coordinator',
  imports: [Footer,RouterOutlet,RouterLink, Navbar,CoordinatorGuide],
  templateUrl: './coordinator.html',
  styleUrl: './coordinator.css'
})
export class Coordinator {


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
