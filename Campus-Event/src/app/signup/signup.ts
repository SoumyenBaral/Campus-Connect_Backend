import { Component, NgModule } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms'; // 1. Import for template-driven forms
import { Router, RouterLink, RouterOutlet } from '@angular/router'; 
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-signup',
  standalone: true,
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
    role: 'STUDENT' as Role ,

    id: 0, 
    createdAt: '', 
    isApproved: false
  };
adddata() {
  // Add client-side check for exactly 10 digits
    const contactPattern = /^\d{10}$/;
    if (!contactPattern.test(this.user.contact)) {
      alert('Contact number must be exactly 10 digits (numbers only).');
      return;
    }
    const payload = {
        name: this.user.name,
        email: this.user.email,
        password: this.user.password,
        contact: this.user.contact,
        role: this.user.role 
        // Note: id, createdAt, isApproved are omitted
    };
    this.http.post("http://localhost:8080/api/postuser", payload, {responseType: 'text'}).subscribe({
      next: (res) => {
        console.log('Signup Successful:', res);
        // 2. Redirect to login page on success
        this.router.navigate(['/login']); 
      },
      error: (err) => console.error('Submission failed:', err)
    });
  }
 

 
}
