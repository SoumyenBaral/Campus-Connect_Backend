import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';



@Component({
  selector: 'app-admin',
  imports: [CustomPipe, Footer, RouterLink, HomeGallery,RouterOutlet ],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";
  menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }


}
