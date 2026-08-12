import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-dance-gallery',
  imports: [RouterLink,RouterOutlet,Navbar,Footer],
  templateUrl: './dance-gallery.html',
  styleUrl: './dance-gallery.css'
})
export class DanceGallery {

}
