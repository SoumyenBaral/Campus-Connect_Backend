import { Component } from '@angular/core';

import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';



@Component({
  selector: 'app-admin',
  imports: [CustomPipe, RouterLink, HomeGallery,RouterOutlet ,Footer],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";



}
