import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user/user';


export interface LoginRequest { 
    email: string; 
    password: string; 
}

@Injectable({ providedIn: 'root' })
export class UserService {
  
  private baseUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }

  // Sign Up
  registerUser(user: User): Observable<string> {
    const url = `${this.baseUrl}/postuser`;
    return this.http.post(url, user, { responseType: 'text' });
  }

  // Login
  login(request: LoginRequest): Observable<string> {
      const url = `${this.baseUrl}/login`;
      return this.http.post(url, request, { responseType: 'text' });
  }

  // Role Management
  storeUserRole(role: string): void {
      localStorage.setItem('userRole', role);
  }

  getUserRole(): string | null {
      return localStorage.getItem('userRole');
  }
}