import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { OngoingEvents } from "../ongoing-events/ongoing-events";

@Component({
  selector: 'app-host',
  imports: [Footer, OngoingEvents],
  templateUrl: './host.html',
  styleUrl: './host.css'
})
export class Host {

}
