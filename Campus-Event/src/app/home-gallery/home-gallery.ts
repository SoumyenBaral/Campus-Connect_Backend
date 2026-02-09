import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from "../navbar/navbar";

@Component({
  selector: 'app-home-gallery',
  imports: [RouterLink, RouterOutlet, Navbar],
  templateUrl: './home-gallery.html',
  styleUrl: './home-gallery.css'
})
export class HomeGallery {

}
