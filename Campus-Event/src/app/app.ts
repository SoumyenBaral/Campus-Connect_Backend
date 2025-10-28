
import { Component} from '@angular/core';
import { Login } from './login/login';
import { Footer } from './footer/footer';
import { RouterLink,RouterOutlet } from '@angular/router';
import { Navbar } from './navbar/navbar';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminGuide } from './admin-guide/admin-guide';
import { UserGuide } from './user-guide/user-guide';
import { HostGuide } from './host-guide/host-guide';
import { CoordinatorGuide } from './coordinator-guide/coordinator-guide';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
