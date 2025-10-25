

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service'; // Import your UserService

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private userService: UserService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    // 1. Get the required role(s) from the route configuration
    // We assume you will add data: { roles: ['ADMIN', 'HOST'] } to your routes
    const requiredRoles = route.data['roles'] as string[];
    
    // 2. Get the currently logged-in user's role from storage
    const userRole = this.userService.getUserRole();

    // Check if the user is logged in (i.e., has a role stored)
    if (!userRole) {
      // Not logged in: redirect to login page
      alert('You must log in to access this page.');
      return this.router.createUrlTree(['/login']);
    }

    // Check if the user's role is included in the required roles for the page
    if (requiredRoles && requiredRoles.includes(userRole)) {
      // 3. Role matches: Allow access
      return true;
    } 
    
    // 4. Role does not match: Redirect to the user's own dashboard
    alert('Access Denied. You do not have the required permissions.');
    
    // A better approach is to navigate them to their default page
    switch (userRole) {
        case 'ADMIN': return this.router.createUrlTree(['/admin']);
        case 'HOST': return this.router.createUrlTree(['/host']);
        case 'COORDINATOR': return this.router.createUrlTree(['/coordinator']);
        case 'STUDENT': 
        default: return this.router.createUrlTree(['/student']);
    }

    
    // Redirect the denied user back to the login page as a failsafe
  //   return this.router.createUrlTree(['/login']);
  }
}