import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Footer } from '../footer/footer';


@Component({
  selector: 'app-ongoing-events',
  imports: [RouterLink,Footer],
  templateUrl: './ongoing-events.html',
  styleUrl: './ongoing-events.css'
})
export class OngoingEvents {

}
