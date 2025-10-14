
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { RegisterPage } from "../register-page/register-page";
import { Signup } from '../signup/signup';
import { CreateEvent } from '../create-event/create-event';



@Component({
  selector: 'app-home',
  imports: [Footer, Navbar,CreateEvent],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
