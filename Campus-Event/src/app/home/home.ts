
import { Component} from '@angular/core';
import { Herosection } from "../herosection/herosection";
import { Footer } from "../footer/footer";

@Component({
  selector: 'app-home',
  imports:[Herosection, Footer],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
