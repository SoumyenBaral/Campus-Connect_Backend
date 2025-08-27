import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';

@Component({
  selector: 'app-host',
  imports: [Footer,CustomPipe],
  templateUrl: './host.html',
  styleUrl: './host.css'
})
export class Host {
name:string="Host";
  menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}
