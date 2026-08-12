import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
  menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  constructor(private router: Router) { }
  

 isLoggedIn = false;

ngOnInit() {
  this.isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
}

logout() {
  localStorage.clear();
  this.isLoggedIn = false;
  this.router.navigate(['/login']);
}



}