
import { Component} from '@angular/core';
import { Herosection } from "../herosection/herosection";
import { Footer } from "../footer/footer";
import { Feedback } from "../feedback/feedback";
import { HomeGallery } from "../home-gallery/home-gallery";

@Component({
  selector: 'app-home',
  imports: [Herosection, Footer,Feedback, HomeGallery],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
