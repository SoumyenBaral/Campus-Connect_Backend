import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Base URL of your Spring Boot Backend
  private apiUrl = 'http://localhost:8080/api';  
  
  // Standard HTTP headers for JSON content
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  // Helper function to get the current user object from localStorage
  private getCurrentUser(): User | null {
    const userJson = localStorage.getItem('currentUser');
    return userJson ? JSON.parse(userJson) : null;
  }

  // FIX 2: Move the isLoggedIn method definition inside the class body
  public isLoggedIn(): boolean {
    return !!this.getCurrentUser();
  }

  getDashboardRoute(): string {
    const user = this.getCurrentUser();
    
    if (!user || !user.role) {
      return '/login'; // Default back to login if no user or role is found
    }
    
    // Map the Spring Boot Role enum strings to Angular paths
    switch (user.role) {
      case 'STUDENT':
        return '/student';
      case 'ADMIN':
        return '/admin';
      case 'COORDINATOR':
        return '/coordinator';
      case 'HOST':
        return '/host';
      default:
        return '/login'; // Fallback
    }
  }

  /**
   * Connects to the POST /api/postuser endpoint for user registration (Sign Up).
   */
  signup(user: User): Observable<string> {
    // FIX 3: Ensure template literal syntax uses backticks (`)
    const signupUrl = `${this.apiUrl}/postuser`; 
    return this.http.post<string>(signupUrl, user, this.httpOptions);
  }

  /**
   * Connects to the POST /api/login endpoint for user authentication.
   */
  login(credentials: LoginRequest): Observable<User> {
    // FIX 4: Ensure template literal syntax uses backticks (`)
    const loginUrl = `${this.apiUrl}/login`; 
    // The response is the User object upon success, or an error.
    return this.http.post<User>(loginUrl, credentials, this.httpOptions);
  }
  
} 