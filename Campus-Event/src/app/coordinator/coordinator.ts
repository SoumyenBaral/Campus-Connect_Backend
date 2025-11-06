import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { CoordinatorGuide } from '../coordinator-guide/coordinator-guide';

@Component({
  selector: 'app-coordinator',
  imports: [Footer,RouterOutlet,RouterLink, Navbar,CoordinatorGuide],
  templateUrl: './coordinator.html',
  styleUrl: './coordinator.css'
})
export class Coordinator {
name:string="Co-ordinator";
  menuOpen: boolean = false;

 

}
