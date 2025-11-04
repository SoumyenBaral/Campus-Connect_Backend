import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-details.html',
  styleUrl: './student-details.css'
})
export class StudentDetails implements OnInit {
students: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.fetchStudents();
  }
  fetchStudents() {
    this.http.get<any[]>('http://localhost:8080/api/getuser').subscribe({
      next: (data) => {
        this.students = data.filter(user => user.role === 'STUDENT');
      },
      error: (err) => console.error('Error fetching students:', err)
    });
}
}
