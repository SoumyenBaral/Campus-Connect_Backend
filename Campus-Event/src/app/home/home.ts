
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { SignUp} from '../signup/signup';
import { RouterOutlet } from '@angular/router';





@Component({
  selector: 'app-home',
  imports: [Footer, Navbar, SignUp, RouterOutlet],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
