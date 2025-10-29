import { Component } from '@angular/core';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';
import {  AuthService } from '../services/auth.service';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [RouterLink,RouterOutlet,FormsModule ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
// Data model for the form inputs (email and password)
    
       loginData: LoginRequest = { email: '', password: '' };
        errorMessage: string = '';

  constructor(private authService: AuthService,private router:Router) { }

  onSubmit(): void {
    this.authService.login(this.loginData).subscribe({
      next: (user: User) => {
        // Handle successful login
        console.log('Login Successful:', user);
        localStorage.setItem('currentUser',JSON.stringify(user));

        // 2. Get the role-specific route
        const redirectRoute = this.authService.getDashboardRoute();
        
        // 3. Redirect the user
        this.router.navigate([redirectRoute]);
        this.errorMessage = '';
      },
      error: (err: any) => {
        // Handle login failure (e.g., 401 Unauthorized)
        console.error('Login Failed:', err);
        this.errorMessage = 'Invalid email or password. Please try again.';
      }
    });   

    
}
}
