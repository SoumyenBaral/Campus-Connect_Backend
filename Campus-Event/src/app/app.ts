
import { Component } from '@angular/core';
import { Admin } from "./admin/admin";
import { Host } from './host/host';
import { Coordinator } from './coordinator/coordinator';
import { Student } from './student/student';
import { Login } from './login/login';
import { Signup } from './signup/signup';
import { Footer } from './footer/footer';
import { Herosection } from './herosection/herosection';
import { ContactUs } from './contact-us/contact-us';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Home } from './home/home';
import { Helpcenter } from './helpcenter/helpcenter';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Home, Footer,Admin,Host,Coordinator,Login,Signup,Helpcenter,ContactUs,Herosection],
  standalone: true,
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'Campus-Event';
}
