
import { Component } from '@angular/core';
import { Admin } from "./admin/admin";
import { Host } from './host/host';
import { Coordinator } from './coordinator/coordinator';
import { Student } from './student/student';
import { Login } from './login/login';
import { Signup } from './signup/signup';
import { Footer } from './footer/footer';
import { ContactUs } from './contact-us/contact-us';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Home } from './home/home';
import { Helpcenter } from './helpcenter/helpcenter';
import { Navbar } from './navbar/navbar';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterPage } from './register-page/register-page';

@Component({
  selector: 'app-root',
  imports: [Footer, RouterLink, RouterOutlet,Navbar, NgbModule,RegisterPage],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'Campus-Event';
}
