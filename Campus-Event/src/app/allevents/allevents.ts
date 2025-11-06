import { Component, OnInit } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterOutlet } from '@angular/router';
import { DatePipe, LowerCasePipe } from '@angular/common';


interface Event{id: number;
  title: string;
  date: Date; // Use JavaScript Date objects for easy comparison
  time: string;
  venue: string;
  category: 'MUSIC' | 'DANCE' | 'TECH';
  imageUrl: string;}

@Component({
  selector: 'app-allevents',
  imports: [Footer,RouterOutlet,DatePipe,LowerCasePipe],
  templateUrl: './allevents.html',
  styleUrl: './allevents.css'
})
export class Allevents   implements OnInit {
 // This variable controls which div block is visible in the HTML (using *ngIf)
  // It is initialized to show Ongoing/Past events first.
  currentView: 'Ongoing' | 'Upcoming' = 'Ongoing';

  constructor() { }

  ngOnInit(): void {
    // You could potentially check the current route here and set currentView accordingly
    // For this combined approach, initializing to 'Ongoing' is typical.
  }

  /**
   * Switches the active event view based on the button clicked.
   * @param view The view to switch to ('Ongoing' or 'Upcoming').
   */
  switchView(view: 'Ongoing' | 'Upcoming'): void {
    this.currentView = view;
    console.log(`Switched event view to: ${this.currentView}`);

    // Optional: You could update the URL here without navigating away if needed (using Router)
    // this.router.navigate([], { queryParams: { view: view } });
  }


}
