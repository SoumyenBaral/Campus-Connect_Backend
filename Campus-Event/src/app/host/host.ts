import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { Login } from "../login/login";
import { CustomPipe } from '../pipes/custom-pipe';

@Component({
  selector: 'app-host',
  imports: [Footer,CustomPipe,Login],
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
