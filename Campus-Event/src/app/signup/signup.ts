import { Component, NgModule } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms'; // 1. Import for template-driven forms
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule, FormsModule, HttpClientModule],
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
    role: 'SELECT ROLE' as Role,

    id: 0,
    createdAt: '',
    isApproved: false
  };
  otpSent: boolean = false;
  enteredOtp: string = '';

  resendOtp() {

  this.http.post("http://localhost:8080/api/send-otp",
    null,
    { params: { email: this.user.email }, responseType: 'text' }
  ).subscribe({
    next: () => alert("OTP resent successfully"),
    error: (err) => console.error("Resend failed", err)
  });
}

//verify otp 




  adddata() {



    const contactPattern = /^\d{10}$/;
    if (!contactPattern.test(this.user.contact)) {
      alert('Contact number must be exactly 10 digits.');
      return;
    }

    // STEP 1 → If OTP not sent, send OTP
    if (!this.otpSent) {

      this.http.post("http://localhost:8080/api/send-otp",
        null,
        { params: { email: this.user.email }, responseType: 'text' }
      ).subscribe({
        next: (res) => {
          alert("OTP sent to your email");
          this.otpSent = true;
        },
        error: (err) => {
          console.error("OTP sending failed", err);
        }
      });

    }

    // STEP 2 → If OTP already sent, verify OTP
    else {

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
            next: (response) => {
              alert("Signup successful!");
              this.router.navigate(['/login']);
            },
            error: (err) => console.error("User save failed", err)
          });

        },
        error: (err) => {
          alert("Invalid or expired OTP");
        }
      });

    }
  }
}