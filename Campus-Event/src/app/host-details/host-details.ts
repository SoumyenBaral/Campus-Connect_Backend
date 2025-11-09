import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-host-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './host-details.html',
  styleUrl: './host-details.css'
})
export class HostDetails implements OnInit {
  hosts: any[] = [];
  unapprovedHosts: any[] = [];

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.fetchAllHostData();
  }
  
  fetchAllHostData() {
    this.fetchHosts();
    this.fetchUnapprovedHosts();
  }
  fetchHosts() {
    this.http.get<any[]>('http://localhost:8080/api/getuser').subscribe({
      next: (data) => {
        this.hosts = data.filter(user => user.role === 'HOST');
      },
      error: (err) => console.error('Error fetching hosts:', err)
    });
  }
   fetchUnapprovedHosts() {
    console.log('Fetching unapproved hosts....')
    this.http.get<any[]>('http://localhost:8080/api/unapproved-hosts').subscribe({
      next: (data) => {
        this.unapprovedHosts = (data);
      },
      error: (err) => console.error('Error fetching unapproved hosts:', err)
    });
  }
  approveHost(id: number) {
    this.http.put(`http://localhost:8080/api/approve/${id}?approve=true`, {}, { responseType: 'text' }).subscribe({
      next: (res) => {
        alert(res);
        this.fetchAllHostData();  // Refresh list
      },
      error: (err) => alert('Error: ' + err.error)
    });
  }

  rejectHost(id: number) {
    this.http.put(`http://localhost:8080/api/approve/${id}?approve=false`, {}, { responseType: 'text' }).subscribe({
      next: (res) => {
        alert(res);
        this.fetchAllHostData();  // Refresh list
      },
      error: (err) => alert('Error: ' + err.error)
    });
  }
}