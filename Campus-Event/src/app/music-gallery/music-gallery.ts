import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { Footer } from "../footer/footer";

@Component({
  selector: 'app-music-gallery',
  imports: [RouterLink, RouterOutlet, Navbar, Footer],
  templateUrl: './music-gallery.html',
  styleUrl: './music-gallery.css'
})
export class MusicGallery {

}
