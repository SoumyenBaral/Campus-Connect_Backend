import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

// CRITICAL: Ensure this import path and class name are correct for your service
import {  AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  // FIX: Inject the AuthService with a consistent name (authService)
  constructor(
    private authService: AuthService, 
    private router: Router
  ) {}

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    // Check if the user is currently logged in via the service
    if (this.authService.isLoggedIn()) {
      return true; // User is logged in, grant access to the requested route
    }
    
    // User is NOT logged in. Redirect them to the login page.
    // The createUrlTree method is used to return a redirect command.
    return this.router.createUrlTree(['/login']); 
  }
}