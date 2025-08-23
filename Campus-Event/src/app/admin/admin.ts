import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { CustomPipe } from '../pipes/custom-pipe';


@Component({
  selector: 'app-admin',
  imports: [CustomPipe,Footer],

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
