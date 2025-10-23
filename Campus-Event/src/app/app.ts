
import { Component, HostBinding, NgModule } from '@angular/core';
import { Admin } from "./admin/admin";
import { Host } from './host/host';
import { Coordinator } from './coordinator/coordinator';
import { Student } from './student/student';
import { Login } from './login/login';
import { Footer } from './footer/footer';
import { ContactUs } from './contact-us/contact-us';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { Home } from './home/home';
import { Helpcenter } from './helpcenter/helpcenter';
import { Navbar } from './navbar/navbar';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminGuide } from './admin-guide/admin-guide';
import { UserGuide } from './user-guide/user-guide';
import { HostGuide } from './host-guide/host-guide';
import { CoordinatorGuide } from './coordinator-guide/coordinator-guide';
import { User } from './user/user';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { routes } from './app.routes';
import { SignUp} from './signup/signup';





@Component({
  selector: 'app-root',
  imports: [Footer, RouterLink, RouterOutlet, Navbar, NgbModule, AdminGuide, UserGuide, HostGuide, CoordinatorGuide,FormsModule, HttpClientModule,Login,SignUp],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App {
  protected title = 'Campus-Event';
}
