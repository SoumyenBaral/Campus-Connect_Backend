// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-reset-password',
//   imports: [],
//   templateUrl: './reset-password.html',
//   styleUrl: './reset-password.css'
// })
// export class ResetPassword {
//  token: string = '';
//   newPassword: string = '';
//   message: string = '';

//   constructor(private route: ActivatedRoute,
//               private authService: AuthService) {

//     this.route.queryParams.subscribe(params => {
//       this.token = params['token'];
//     });
//   }

//   resetPassword() {
//     this.authService.resetPassword(this.token, this.newPassword)
//       .subscribe({
//         next: () => this.message = 'Password updated successfully!',
//         error: () => this.message = 'Invalid or expired token.'
//       });
//   }
// }
