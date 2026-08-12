import { Component } from '@angular/core';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';
import { AuthService } from '../services/auth.service';
import { RouterLink, RouterOutlet, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Navbar } from '../navbar/navbar';
import { firstValueFrom } from 'rxjs'; // Required for async/await

@Component({
    selector: 'app-login',
    standalone: true, // Assuming Angular 17+ based on your imports style
    imports: [RouterLink, RouterOutlet, FormsModule, Navbar],
    templateUrl: './login.html',
    styleUrl: './login.css'
})
export class Login {
    loginData: LoginRequest = { email: '', password: '' };
    errorMessage: string = '';
    isLoading: boolean = false; // New loading state

    constructor(private authService: AuthService, private router: Router) { }

    async onSubmit(): Promise<void> {
        this.isLoading = true;
        this.errorMessage = '';

        try {
            // Convert the Observable to a Promise
            const user = await firstValueFrom(this.authService.login(this.loginData));

            console.log('Login Successful:', user);

            // Save user state via service
            this.authService.saveUser(user);
            
            // Persist session info
            localStorage.setItem("isLoggedIn", "true");
            localStorage.setItem("role", user.role);

            // Navigate to appropriate dashboard
            const redirectRoute = this.authService.getDashboardRoute();
            await this.router.navigate([redirectRoute]);

        } catch (err: any) {
            console.error('Login Failed:', err);
            this.errorMessage = 'Invalid email or password. Please try again.';
        } finally {
            // This runs whether the request succeeded OR failed
            this.isLoading = false;
        }
    }
}