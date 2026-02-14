import { Component } from '@angular/core';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';
import { AuthService } from '../services/auth.service';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
    selector: 'app-login',
    imports: [RouterLink, RouterOutlet, FormsModule],
    templateUrl: './login.html',
    styleUrl: './login.css'
})
export class Login {

    loginData: LoginRequest = { email: '', password: '' };
    errorMessage: string = '';

    constructor(private authService: AuthService, private router: Router) { }

    onSubmit(): void {
        this.authService.login(this.loginData).subscribe({
            next: (user: User) => {
                console.log('Login Successful:', user);

                // 1. Keep the high-level service call to save the user
                this.authService.saveUser(user);
                // 2. Remove the redundant localStorage.setItem() call

                const redirectRoute = this.authService.getDashboardRoute();
                this.router.navigate([redirectRoute]);
                // 3. Remove the second redundant this.router.navigate() call


                localStorage.setItem("isLoggedIn", "true");
                localStorage.setItem("role", user.role);

                this.errorMessage = '';
            },
            error: (err: any) => {
                console.error('Login Failed:', err);
                this.errorMessage = 'Invalid email or password. Please try again.';
            }
        });


    }
}
