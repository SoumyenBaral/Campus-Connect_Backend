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
  constructor(private http: HttpClient, private router: Router) {
  }
  user: User = {
    name: '',
    email: '',
    password: '',
    contact: '',
    role: 'STUDENT' as Role 
  };
adddata() {
    this.http.post("http://localhost:8080/api/postuser", this.user, {responseType: 'text'}).subscribe({
      next: (res) => {
        console.log('Signup Successful:', res);
        // 2. Redirect to login page on success
        this.router.navigate(['/login']); 
      },
      error: (err) => console.error('Submission failed:', err)
    });
  }
 

 
}
