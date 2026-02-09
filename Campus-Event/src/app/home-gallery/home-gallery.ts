import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from "../navbar/navbar";
import { Navbar } from '../navbar/navbar';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-gallery',
  standalone: true,
  imports: [RouterLink,RouterOutlet,Navbar , CommonModule],
  templateUrl: './home-gallery.html',
  styleUrl: './home-gallery.css'
})


export class HomeGallery {

}
