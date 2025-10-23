import { Component, NgModule } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms'; // 1. Import for template-driven forms
import { Router, RouterLink, RouterOutlet } from '@angular/router'; 
import { UserService } from '../services/user.service'; // Import the service
import { Role, User } from '../user/user';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-signup',
  imports:[RouterOutlet,RouterLink, CommonModule,FormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class SignUp {
  
  // 2. Initialize the User object. Angular will fill this object automatically.
  user: User = {
    name: '',
    email: '',
    password: '',
    contact: '',
    role: 'STUDENT' as Role // Default value for the dropdown
  };
signupForm: any;

  constructor(private userService: UserService, private router: Router) { }

  // 3. The onSubmit function receives the NgForm object
  onSubmit(form: NgForm) {
    if (form.valid) {
      // 4. Send the automatically populated 'this.user' object directly to the service
      this.userService.registerUser(this.user).subscribe({
        next: (response) => {
          alert('Registration Successful: ' + response);
          this.router.navigate(['/login']); 
        },
        error: (error) => {
          console.error('Registration Failed:', error);
          alert('Registration failed. Please try again.');
        }
      });
    } else {
      alert('Please fill out all required fields.');
    }
  }
}
