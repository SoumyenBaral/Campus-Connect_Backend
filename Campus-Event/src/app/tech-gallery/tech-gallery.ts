import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-tech-gallery',
  imports: [RouterLink,RouterOutlet,Navbar,Footer],
  templateUrl: './tech-gallery.html',
  styleUrl: './tech-gallery.css'
})
export class TechGallery {

}
