import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-details',
  imports: [CommonModule],
  templateUrl: './user-details.html',
  styleUrl: './user-details.css'
})
export class UserDetails implements OnInit{
users: any;
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.fetchUsers();
  }
  fetchUsers() {

    this.http.get<any[]>('http://localhost:8080/api/getuser').subscribe({
      next: (data) => {
        this.users = data.filter(user => user.role === 'USER');
      },
      error: (err) => console.error('Error fetching users:', err)
  })
  // fetchStudents() {
  //   this.http.get<any[]>('http://localhost:8080/api/getuser').subscribe({
  //     next: (data) => {
  //       this.students = data.filter(user => user.role === 'USER');
  //     },
  //     error: (err) => console.error('Error fetching users:', err)
  //   });
}
}
