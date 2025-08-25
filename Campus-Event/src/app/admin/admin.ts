import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-admin',
  imports: [CustomPipe, Footer, RouterLink],

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
