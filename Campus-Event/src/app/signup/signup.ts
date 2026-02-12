import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ RouterLink, CommonModule, FormsModule, HttpClientModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class SignUp {

  constructor(private http: HttpClient, private router: Router) {}

  user: User = {
    name: '',
    email: '',
    password: '',
    contact: '',
    role: '' as Role,
    id: 0,
    createdAt: '',
    isApproved: false
  };

  otpSent: boolean = false;
  enteredOtp: string = '';

  // 🔹 MAIN BUTTON FUNCTION
  adddata() {

    const contactPattern = /^\d{10}$/;
    if (!contactPattern.test(this.user.contact)) {
      alert('Contact number must be exactly 10 digits.');
      return;
    }


  

    // 🟢 STEP 1 → SEND OTP (First Click)
    if (!this.otpSent) {

      this.http.post("http://localhost:8080/api/send-otp",
        null,
        {
          params: { email: this.user.email },
          responseType: 'text'
        }
      ).subscribe({
        next: () => {
          alert("OTP sent to your email");
          this.otpSent = true;  // show OTP field
        },
        error: (err) => {
          console.error("OTP send failed", err);
        }
      });

    }

    // 🔵 STEP 2 → VERIFY OTP + REGISTER (Second Click)
    else {
      if (!this.enteredOtp) {
      alert("Please enter OTP");
      return;
    }
    if (!/^\d{6}$/.test(this.enteredOtp)) {
  alert("OTP must be exactly 6 digits");
  return;
}
      this.http.post("http://localhost:8080/api/verify-otp",
        null,
        {
          params: {
            email: this.user.email,
            otp: this.enteredOtp
          },
          responseType: 'text'
        }
      ).subscribe({
        next: (res) => {

   

          // OTP verified → Now register user
          const payload = {
            name: this.user.name,
            email: this.user.email,
            password: this.user.password,
            contact: this.user.contact,
            role: this.user.role
          };

          this.http.post("http://localhost:8080/api/postuser",
            payload,
            { responseType: 'text' }
          ).subscribe({
            next: () => {
              alert("Signup successful!");
              this.router.navigate(['/login']);
            },
            error: (err) => console.error("User save failed", err)
          });

        },
        error: (err) => {
          alert("Invalid OTP! Please try again.");
          console.error("OTP verification failed", err);
        }
      });

    }
  }

  // 🔁 RESEND OTP
  resendOtp() {
    this.http.post("http://localhost:8080/api/send-otp",
      null,
      { params: { email: this.user.email }, responseType: 'text' }
    ).subscribe({
      next: () => alert("OTP resent successfully"),
      error: (err) => console.error("Resend failed", err)
    });
  }
}