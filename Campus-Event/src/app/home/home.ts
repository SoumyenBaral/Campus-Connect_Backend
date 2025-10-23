
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { Signup } from '../signup/signup';




@Component({
  selector: 'app-home',
  imports: [Footer, Navbar,Signup],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
