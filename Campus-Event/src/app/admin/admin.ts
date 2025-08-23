import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CustomPipe } from '../pipes/custom-pipe';

@Component({
  selector: 'app-admin',
  imports: [RouterOutlet,RouterLink,CustomPipe],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin {
name:string="Admin";
sidebarOpen = false;

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;

}
}
