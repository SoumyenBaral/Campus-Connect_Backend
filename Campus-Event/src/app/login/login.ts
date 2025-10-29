import { Component } from '@angular/core';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';
import {  AuthService } from '../services/auth.service';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [RouterLink,RouterOutlet,FormsModule,CommonModule ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
    
       loginData: LoginRequest = { email: '', password: '' };
  errorMessage: string = '';

  constructor(private authService: AuthService,private router:Router) { }

  onSubmit(): void {
    this.authService.login(this.loginData).subscribe({
      next: (user: User) => {
        console.log('Login Successful:', user);
        localStorage.setItem('currentUser',JSON.stringify(user));

        const redirectRoute = this.authService.getDashboardRoute();
        
        this.router.navigate([redirectRoute]);
        this.errorMessage = '';
      },
      error: (err: any) => {
        console.error('Login Failed:', err);
        this.errorMessage = 'Invalid email or password. Please try again.';
      }
    });   

    
}
}
