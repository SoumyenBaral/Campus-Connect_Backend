
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { RouterOutlet } from '@angular/router';
import { CreateEvent } from '../create-event/create-event';
import { SignUp } from '../signup/signup';
import { Coordinator } from '../coordinator/coordinator';
import { Host } from "../host/host";
import { OngoingEvents } from "../ongoing-events/ongoing-events";
import { UpcomingEvents } from "../upcoming-events/upcoming-events";
import { Feedback } from "../feedback/feedback";

@Component({
  selector: 'app-home',
  imports: [Footer, Navbar, CreateEvent, RouterOutlet, SignUp, Coordinator, Host, OngoingEvents, UpcomingEvents, Feedback],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
