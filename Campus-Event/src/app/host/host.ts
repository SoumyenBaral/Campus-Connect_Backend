import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Feedback } from "../feedback/feedback";


@Component({
  selector: 'app-host',
  imports: [Footer, CustomPipe, RouterOutlet, RouterLink, Navbar, Feedback],
  templateUrl: './host.html',
  styleUrl: './host.css'
})
export class Host {
name:string="Host";
  menuOpen: boolean = false;

}
