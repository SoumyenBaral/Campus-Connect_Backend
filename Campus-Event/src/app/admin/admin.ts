import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Home } from '../home/home';
import { Navbar } from '../navbar/navbar';
import { Footer } from '../footer/footer';

@Component({
  selector: 'app-admin',
  imports: [RouterOutlet,RouterLink,Navbar,Footer],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {

}
