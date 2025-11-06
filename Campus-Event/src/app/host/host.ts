import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Feedback } from "../feedback/feedback";
import { HostGuide } from '../host-guide/host-guide';
import { CreateEvent } from '../create-event/create-event';


@Component({
  selector: 'app-host',
  imports: [Footer, RouterOutlet, RouterLink, Navbar,HostGuide,CreateEvent],
  templateUrl: './host.html',
  styleUrl: './host.css'
})
export class Host {
name:string="Host";
  menuOpen: boolean = false;

}
