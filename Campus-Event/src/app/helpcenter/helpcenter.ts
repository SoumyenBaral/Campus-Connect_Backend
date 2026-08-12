import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterLink,RouterOutlet } from '@angular/router';



@Component({
  selector: 'app-helpcenter',
  imports: [RouterLink, RouterOutlet],
  templateUrl: './helpcenter.html',
  styleUrl: './helpcenter.css'
})
export class Helpcenter {

}
