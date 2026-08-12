import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree {
    
  
    const expectedRole = route.data['expectedRole']; 

   
    const user = (this.authService as any).getCurrentUser(); 
    
    if (user && user.role && user.role === expectedRole) {
      return true; // Role matches, grant access
    }

   
    const redirectPath = user ? '/student' : '/login'; 
    
    return this.router.createUrlTree([redirectPath]); 
  }
}