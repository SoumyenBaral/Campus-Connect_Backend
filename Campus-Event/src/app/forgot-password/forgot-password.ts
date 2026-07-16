import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css'
})
export class ForgotPassword {
 email: string = '';
  message: string = '';

  constructor(private authService: AuthService) {}

  sendResetLink() {
    this.authService.forgotPassword(this.email).subscribe({
      next: () => {
        this.message = 'Reset link sent to your email.';
      },
      error: () => {
        this.message = 'Email not found.';
      }
    });
  }
}
