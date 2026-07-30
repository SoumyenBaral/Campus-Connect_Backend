import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Navbar } from '../navbar/navbar';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule, HttpClientModule, Navbar],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class SignUp {
signupForm: any;
  constructor(private http: HttpClient, private router: Router) {}

  user: User = {
    name: '',
    email: '',
    password: '',
    contact: '',
    role: '' as Role,
    id: 0,
    createdAt: '',
    isApproved: false,
  };

  otpSent: boolean = false;
  enteredOtp: string = '';
  isLoading: boolean = false;

  message: string = '';

  // 🔁 Resend OTP variables
  resendDisabled: boolean = false;
  countdown: number = 0;
  timer: any;

  // ====================================
  // MAIN SIGNUP FUNCTION
  // ====================================
  async adddata() {
    this.message = '';
    this.isLoading = true;

    const contactPattern = /^\d{10}$/;
    if (!contactPattern.test(this.user.contact)) {
      this.message = 'Contact number must be exactly 10 digits.';
      this.isLoading = false;
      return;
    }

    try {
      // ==========================
      // STEP 1: SEND OTP
      // ==========================
      if (!this.otpSent) {
        await firstValueFrom(
          this.http.post('http://localhost:8080/api/send-otp', null, {
            params: { email: this.user.email },
            responseType: 'text',
          })
        );

        // 🔥 This makes OTP input appear
        this.otpSent = true;

        // Start resend countdown
        this.startCountdown(30);

        this.message = 'OTP sent to your email.';
      }

      // ==========================
      // STEP 2: VERIFY OTP & REGISTER
      // ==========================
      else {
        if (!/^\d{6}$/.test(this.enteredOtp)) {
          this.message = 'OTP must be exactly 6 digits.';
          this.isLoading = false;
          return;
        }

        // 1️⃣ Verify OTP
        await firstValueFrom(
          this.http.post('http://localhost:8080/api/verify-otp', null, {
            params: {
              email: this.user.email,
              otp: this.enteredOtp,
            },
            responseType: 'text',
          })
        );

        const payload = {
          name: this.user.name,
          email: this.user.email,
          password: this.user.password,
          contact: this.user.contact,
          role: this.user.role,
        };

        // 2️⃣ Save user to DB
        await firstValueFrom(
          this.http.post('http://localhost:8080/api/postuser', payload, {
            responseType: 'text',
          })
        );

        // 3️⃣ Send confirmation email (background)
        this.http.post(
          'http://localhost:8080/api/confirm-mail',
          payload,
          { responseType: 'text' }
        ).subscribe();

        this.message = 'Signup successful! Redirecting...';

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1500);
      }

    } catch (err: any) {
      console.error(err);
      this.message = err?.error || 'Something went wrong. Please try again.';
    } finally {
      this.isLoading = false;
    }
  }

  // ====================================
  // RESEND OTP
  // ====================================
  async resendOtp() {
    if (this.resendDisabled) return;

    this.isLoading = true;
    this.message = '';

    try {
      await firstValueFrom(
        this.http.post('http://localhost:8080/api/send-otp', null, {
          params: { email: this.user.email },
          responseType: 'text',
        })
      );

      this.message = 'OTP resent successfully.';
      this.startCountdown(30);

    } catch (err) {
      this.message = 'Failed to resend OTP.';
    } finally {
      this.isLoading = false;
    }
  }

  // ====================================
  // COUNTDOWN TIMER
  // ====================================
  startCountdown(seconds: number) {
    this.resendDisabled = true;
    this.countdown = seconds;

    if (this.timer) {
      clearInterval(this.timer);
    }

    this.timer = setInterval(() => {
      this.countdown--;

      if (this.countdown <= 0) {
        clearInterval(this.timer);
        this.resendDisabled = false;
      }
    }, 1000);
  }
}
