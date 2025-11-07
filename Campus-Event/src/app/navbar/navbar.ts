import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';

@Component({
  selector: 'app-navbar',
  imports: [RouterOutlet, RouterLink,HomeGallery],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
 menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}
