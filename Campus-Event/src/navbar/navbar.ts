import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink,RouterOutlet],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

}
