import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-coordinator',
  imports: [Footer,CustomPipe,RouterOutlet,RouterLink],
  templateUrl: './coordinator.html',
  styleUrl: './coordinator.css'
})
export class Coordinator {
name:string="Co-ordinator";
  menuOpen: boolean = false;

 

}
