
import { Component} from '@angular/core';
import { Herosection } from "../herosection/herosection";
import { Footer } from "../footer/footer";
import { Gallery } from "../gallery/gallery";

@Component({
  selector: 'app-home',
  imports: [Herosection, Footer, Gallery],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
