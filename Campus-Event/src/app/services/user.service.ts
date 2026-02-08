import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../User.interface';
import { AdminReport } from '../AdminReport.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService { 

  
  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }


  
  getAdminReport(): Observable<AdminReport> {
    return this.http.get<AdminReport>(`${this.apiUrl}/admin/report`);
  }

  
  getUnapprovedHosts(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/admin/hosts/unapproved`);
  }

  
  updateHostApproval(userId: number, status: boolean): Observable<User> {
    // We send an empty body {} and pass 'status' as a query parameter
    return this.http.put<User>(`${this.apiUrl}/admin/hosts/${userId}/approve?status=${status}`, {});
  }
}