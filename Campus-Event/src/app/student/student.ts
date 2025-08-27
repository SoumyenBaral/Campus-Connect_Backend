import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Footer } from '../footer/footer';
import { Feedback } from '../feedback/feedback';
import { CustomPipe } from '../pipes/custom-pipe';

@Component({
  selector: 'app-student',
  imports: [Footer, Feedback, CustomPipe, RouterLink,RouterOutlet],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student {
  name: string = "Student"
  menuOpen: boolean = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}

