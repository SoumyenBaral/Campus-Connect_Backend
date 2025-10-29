import { Component } from '@angular/core';

import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';
import { Feedback } from '../feedback/feedback';



@Component({
  selector: 'app-admin',
  imports: [CustomPipe, RouterLink, HomeGallery,RouterOutlet ,Footer,Feedback],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";



}
