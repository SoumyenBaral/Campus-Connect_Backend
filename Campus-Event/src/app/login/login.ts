import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { LoginRequest, UserService } from '../services/user.service';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [RouterLink,RouterOutlet,CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
// Data model for the form inputs (email and password)
    loginData: LoginRequest = { email: '', password: '' };

    constructor(private userService: UserService, private router: Router) { }

    onLoginSubmit(form: NgForm) {
        if (!form.valid) { return; }

        this.userService.login(this.loginData).subscribe({
            next: (userRole: string) => {
                this.userService.storeUserRole(userRole);
                this.redirectToDashboard(userRole);
            },
            error: (err) => {
                alert('Login failed. Invalid credentials or server error.');
                console.error('Login Error:', err);
            }
        });
    }

    redirectToDashboard(userRole: string): void {
        switch (userRole.toUpperCase()) {
            case 'ADMIN':
                this.router.navigate(['/admin']);
                break;
            case 'HOST':
                this.router.navigate(['/host']);
                break;
            case 'COORDINATOR':
                this.router.navigate(['/coordinator']);
                break;
            case 'STUDENT': 
            default:
                this.router.navigate(['/student']);
                break;
        }
    }
}
