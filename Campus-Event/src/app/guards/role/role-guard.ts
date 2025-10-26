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

    // 2. Safely retrieve the user object from local storage
    const userJson = localStorage.getItem('currentUser');
    const user = userJson ? JSON.parse(userJson) : null;

    // 3. Check if the user is logged in AND has the expected role
    if (user && user.role && user.role === expectedRole) {
      return true; // Role matches, grant access
    }

    // 4. Role mismatch or not logged in, redirect to a default safe dashboard (e.g., student) or login
    // Note: Use a route that is accessible to the current user state
    const redirectPath = user ? '/student' : '/login'; 
    return this.router.createUrlTree([redirectPath]); 
  }
}