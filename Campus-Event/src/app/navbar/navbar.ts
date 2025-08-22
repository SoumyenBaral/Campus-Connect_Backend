import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Admin } from '../admin/admin';
import { Coordinator } from '../coordinator/coordinator';
import { Student } from '../student/student';
import { Host } from '../host/host';
import { Profile } from '../profile/profile';
import { Login } from '../login/login';
import { Helpcenter } from '../helpcenter/helpcenter';
import { ContactUs } from '../contact-us/contact-us';
import { Footer } from '../footer/footer';
import { Herosection } from '../herosection/herosection';
import { Home } from '../home/home';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink,RouterOutlet,Admin,Coordinator,Student,Host,Login,Profile,Helpcenter,ContactUs,Footer,Herosection,Home],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

}
