import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../Login-request.interface';
import { User } from '../User.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = 'http://localhost:8080/api';  
  
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  public getCurrentUser(): User | null {
    const userJson = localStorage.getItem('currentUser');
    return userJson ? JSON.parse(userJson) : null;
  }
  public isLoggedIn(): boolean {
    return !!this.getCurrentUser();
  }
  getDashboardRoute(): string {
    const user = this.getCurrentUser();
    
    if (!user || !user.role) {
      return '/login'; 
    }
    
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

 
  signup(user: User): Observable<string> {
    const signupUrl = `${this.apiUrl}/postuser`; 
    return this.http.post<string>(signupUrl, user, this.httpOptions);
  }

 
  login(credentials: LoginRequest): Observable<User> {
    
    const loginUrl = `${this.apiUrl}/login`; 
    
    return this.http.post<User>(loginUrl, credentials, this.httpOptions);
  }
  
}