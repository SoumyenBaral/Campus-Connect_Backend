import { Component } from '@angular/core';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';
import { Navbar } from '../navbar/navbar';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin',
  imports: [CustomPipe, RouterLink, HomeGallery,RouterOutlet ,Footer,Navbar],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";
counts:any = {};

 constructor(private http: HttpClient) {}
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
