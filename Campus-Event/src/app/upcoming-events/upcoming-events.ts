import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Footer } from '../footer/footer';


@Component({
  selector: 'app-upcoming-events',
  imports: [RouterLink, Footer, RouterOutlet],
  templateUrl: './upcoming-events.html',
  styleUrl: './upcoming-events.css'
})
export class UpcomingEvents {

}
