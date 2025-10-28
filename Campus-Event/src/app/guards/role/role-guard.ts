import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree {
    
    // 1. Get the expected role from the router configuration
    const expectedRole = route.data['expectedRole']; 

    // OPTIMIZATION: Use a private method in AuthService to retrieve user 
    // (We'll assume the private getCurrentUser method is available or accessible)
    // NOTE: If you cannot access private methods, your current local storage read is fine.
    const user = (this.authService as any).getCurrentUser(); 
    // If AuthService.getCurrentUser() is public, use: const user = this.authService.getCurrentUser();

    // 2. Check if the user is logged in AND has the expected role
    // CRITICAL CLARIFICATION: The role property in the User object MUST be named 'role'
    if (user && user.role && user.role === expectedRole) {
      return true; // Role matches, grant access
    }

    // 3. Role mismatch or not logged in, determine redirect path
    // If user exists (logged in, but wrong role), redirect to the generic student path.
    // If user doesn't exist (not logged in), redirect to /login.
    const redirectPath = user ? '/student' : '/login'; 
    
    return this.router.createUrlTree([redirectPath]); 
  }
}