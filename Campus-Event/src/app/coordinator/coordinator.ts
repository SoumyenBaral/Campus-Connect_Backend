import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';

@Component({
  selector: 'app-coordinator',
  imports: [Footer,CustomPipe],
  templateUrl: './coordinator.html',
  styleUrl: './coordinator.css'
})
export class Coordinator {
name:string="Co-ordinator";
  menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

}
