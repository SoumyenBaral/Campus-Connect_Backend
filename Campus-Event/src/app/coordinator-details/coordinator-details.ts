import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coordinator-details',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './coordinator-details.html',
  styleUrl: './coordinator-details.css'
})
export class CoordinatorDetails implements OnInit{
coordinators: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.fetchCoordinators();
  }
  fetchCoordinators() {
    this.http.get<any[]>('http://localhost:8080/api/getuser').subscribe({
      next: (data) => {
        this.coordinators = data.filter(user => user.role === 'COORDINATOR');
      },
      error: (err) => console.error('Error fetching coordinators:', err)
    });
  }
}
