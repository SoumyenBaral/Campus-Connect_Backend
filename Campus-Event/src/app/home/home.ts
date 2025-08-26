
import { Component} from '@angular/core';
import { Footer } from "../footer/footer";

import { HomeGallery } from "../home-gallery/home-gallery";


@Component({
  selector: 'app-home',
  imports: [Footer, HomeGallery],
  templateUrl:'./home.html',
    styleUrl: './home.css'
  })
export class Home {

}
