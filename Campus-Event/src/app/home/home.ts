
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";
import { Feedback } from "../feedback/feedback";
import { HomeGallery } from "../home-gallery/home-gallery";
import { Herosection } from "../herosection/herosection";

@Component({
  selector: 'app-home',
  imports: [Footer, Herosection],
  imports: [Herosection, Footer,Feedback, HomeGallery],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
