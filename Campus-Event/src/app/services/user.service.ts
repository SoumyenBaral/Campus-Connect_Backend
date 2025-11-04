import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../User.interface';
import { AdminReport } from '../AdminReport.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService { // Assuming your service is named UserService

  // 💡 Adjust this URL if your Spring Boot app is on a different port or path
  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }

  // --- Existing methods (login, signup, etc.) would be here ---
  
  // ===================================
  // NEW ADMIN FUNCTIONALITIES
  // ===================================

  /**
   * 1. Fetches aggregated reporting data.
   * Maps to: GET /api/admin/report
   */
  getAdminReport(): Observable<AdminReport> {
    return this.http.get<AdminReport>(`${this.apiUrl}/admin/report`);
  }

  /**
   * 2. Fetches the list of hosts awaiting approval.
   * Maps to: GET /api/admin/hosts/unapproved
   */
  getUnapprovedHosts(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/admin/hosts/unapproved`);
  }

  /**
   * 3. Updates the host approval status (Accept/Reject).
   * Maps to: PUT /api/admin/hosts/{userId}/approve?status={boolean}
   */
  updateHostApproval(userId: number, status: boolean): Observable<User> {
    // We send an empty body {} and pass 'status' as a query parameter
    return this.http.put<User>(`${this.apiUrl}/admin/hosts/${userId}/approve?status=${status}`, {});
  }
}