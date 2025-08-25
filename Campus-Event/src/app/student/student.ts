import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Footer } from '../footer/footer';
import { Feedback } from '../feedback/feedback';

@Component({
  selector: 'app-student',
  imports: [Footer,Feedback],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student {

}
