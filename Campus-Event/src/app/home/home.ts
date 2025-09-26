
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { RegisterPage } from "../register-page/register-page";
import { Gallery } from "../gallery/gallery";


@Component({
  selector: 'app-home',
  imports: [Footer, Navbar, Gallery],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
