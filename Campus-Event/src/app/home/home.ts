import { Component} from '@angular/core';
import { RouterLink,RouterOutlet } from '@angular/router';
import { Herosection } from "../herosection/herosection";
import { Footer } from "../footer/footer";





@Component({
  selector: 'app-home',
  imports: [RouterLink, RouterOutlet, Herosection, Footer],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
