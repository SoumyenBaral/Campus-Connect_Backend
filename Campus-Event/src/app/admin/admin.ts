import { Component } from '@angular/core';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';
import { Navbar } from '../navbar/navbar';



@Component({
  selector: 'app-admin',
  imports: [CustomPipe, RouterLink, HomeGallery,RouterOutlet ,Footer,Navbar],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";



}
