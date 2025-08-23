import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

import { Home } from '../home/home';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';


@Component({
  selector: 'app-admin',
  imports: [RouterOutlet,RouterLink,CustomPipe],

  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";
}
