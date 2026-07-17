import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';
import { Navbar } from '../navbar/navbar';
import { HttpClient } from '@angular/common/http';
import { AdminGuide } from '../admin-guide/admin-guide';
import { CommonModule } from '@angular/common'; // Required for *ngIf
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-admin',
  standalone: true,
  // Add CommonModule to imports to use *ngIf in your template
  imports: [RouterLink, HomeGallery, RouterOutlet, Footer, Navbar, AdminGuide, CommonModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin implements OnInit {
  counts: any = { hostCount: 0, coordinatorCount: 0, studentCount: 0, eventCount: 0 };
  isLoading: boolean = true; // Set to true by default

  constructor(private http: HttpClient) { }

  async ngOnInit() {
    await this.fetchCounts();
  }

  async fetchCounts() {
    this.isLoading = true; // Start loading
    try {
      // firstValueFrom handles the observable and converts it to a Promise
      const data = await firstValueFrom(
        this.http.get<any>('http://localhost:8080/api/counts')
      );
      this.counts = data;
    } catch (err) {
      console.error('Error fetching counts:', err);
      // Fallback defaults stay as initialized
    } finally {
      // This runs regardless of success or failure
      this.isLoading = false; 
    }
  }
}