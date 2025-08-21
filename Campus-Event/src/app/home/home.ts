import { Component} from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Admin } from '../admin/admin';
import { Coordinator } from '../coordinator/coordinator';
import { Student } from '../student/student';
import { Login } from '../login/login';
import { Host } from '../host/host';
import { Profile } from '../profile/profile';
import { Helpcenter } from '../helpcenter/helpcenter';
import { ContactUs } from '../contact-us/contact-us';
import { Footer } from '../footer/footer';
import { Herosection } from '../herosection/herosection';

@Component({
  selector: 'app-home',
  imports: [RouterLink,RouterOutlet,Admin,Coordinator,Student,Host,Login,Profile,Helpcenter,ContactUs,Footer,Herosection],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
