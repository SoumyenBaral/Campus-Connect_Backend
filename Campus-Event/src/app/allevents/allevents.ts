import { Component, OnDestroy, OnInit } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterOutlet, RouterLink, Router, NavigationEnd } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { filterByCategoryPipe } from '../pipes/custom-pipe';
import { CommonModule } from '@angular/common';
import { filter, interval, startWith, Subscription, switchMap } from 'rxjs';


interface Event {
  id: number;
  title: string;
  location: string;
  eventDate: string; // ISO string from backend (e.g., "2026-06-20T11:00:00")
  status: 'UPCOMING' | 'ONGOING' | 'COMPLETED';
  category: 'MUSIC' | 'DANCE' | 'TECH'; // Ensure this field exists in your Events entity
  imageUrl: string; // Placeholder for image URL
}

@Component({
  selector: 'app-allevents',
  standalone: true,
  imports: [Footer, RouterOutlet, CommonModule, HttpClientModule, filterByCategoryPipe, RouterLink],
  templateUrl: './allevents.html',
  styleUrl: './allevents.css',
})
export class Allevents   implements OnInit, OnDestroy{
 
  currentView: 'Ongoing' | 'Upcoming' = 'Ongoing';

  allEvents: Event[] = [];
  ongoingEvents: Event[] = [];
  upcomingEvents: Event[] = [];

  categories = ['MUSIC', 'DANCE', 'TECH']; // Use an array for iteration

  private pollingSubscription!: Subscription;

  constructor(private http: HttpClient, private router: Router) { }

 ngOnInit(): void {
    const pollingIntervalMs = 10000; // Poll every 10 seconds
    
    this.pollingSubscription = interval(pollingIntervalMs)
      .pipe(
        startWith(0), // Ensures it runs immediately on load
        switchMap(() => this.http.get<Event[]>('http://localhost:8080/api/getAllevents',{ responseType: 'json' })) // <-- NOTE: Use your correct endpoint /getAllevents here
      )
      .subscribe({
        next: (events) => {
          this.allEvents = events;
          this.splitEvents(events);
        },
        error: (err) => console.error('Error fetching events via polling:', err)
      });
  }

   ngOnDestroy(): void {
      if (this.pollingSubscription) {
          this.pollingSubscription.unsubscribe();
      }
      // NEW: Unsubscribe from the polling timer
      if (this.pollingSubscription) {
          this.pollingSubscription.unsubscribe();
      }
  }

  splitEvents(events: Event[]): void {
    this.ongoingEvents = [];
    this.upcomingEvents = [];

    events.forEach(event => {
      if (event.status === 'UPCOMING') {
        this.upcomingEvents.push(event);
      } else if (event.status === 'ONGOING' || event.status === 'COMPLETED') {
        this.ongoingEvents.push(event);
      }
    });
  }

  
  switchView(view: 'Ongoing' | 'Upcoming'): void {
    this.currentView = view;
  }
}
