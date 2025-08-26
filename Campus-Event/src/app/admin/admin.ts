import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Gallery } from '../gallery/gallery';


@Component({
  selector: 'app-admin',
  imports: [CustomPipe, Footer, RouterLink, Gallery,RouterOutlet ],

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
