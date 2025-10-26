import { Component, NgModule } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms'; // 1. Import for template-driven forms
import { Router, RouterLink, RouterOutlet } from '@angular/router'; 
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-signup',
  imports:[RouterOutlet,RouterLink, CommonModule,FormsModule,HttpClientModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class SignUp {
submitEvent() {
throw new Error('Method not implemented.');
}
  constructor(private http: HttpClient) {
    // this.getdata();
  }
  // 2. Initialize the User object. Angular will fill this object automatically.
  user: User = {
    name: '',
    email: '',
    password: '',
    contact: '',
    role: 'STUDENT' as Role // Default value for the dropdown
  };
adddata() {
    this.http.post("http://localhost:8080/api/postuser", this.user).subscribe({
      next: (res) => console.log('Contact submitted:', res),
      error: (err) => console.error('Submission failed:', err)
    });
  }
 

  // 3. The onSubmit function receives the NgForm object
 
}
