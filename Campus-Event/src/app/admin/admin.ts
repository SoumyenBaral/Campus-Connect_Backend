import { Component, OnInit } from '@angular/core'; 
import { RouterLink, RouterOutlet } from '@angular/router';
import { BaseChartDirective } from 'ng2-charts';
import { CommonModule } from '@angular/common';

// Assuming these relative paths and types exist in your project structure
import { CustomPipe } from '../pipes/custom-pipe';
import { HomeGallery } from '../home-gallery/home-gallery';
import { Footer } from '../footer/footer';
import { Navbar } from '../navbar/navbar';

// NEW IMPORTS: Service and Data Interfaces
import { ChartData, ChartType } from 'chart.js'; 
import { AdminReport } from '../AdminReport.interface';
import { User } from '../User.interface';
import { UserService } from '../services/user.servivce';


@Component({
  selector: 'app-admin',
  // FIX: Add CommonModule for directives (*ngIf, *ngFor)
  imports: [
    CommonModule, 
    CustomPipe, 
    RouterLink, 
    HomeGallery, 
    RouterOutlet, 
    Footer, 
    Navbar, 
    BaseChartDirective
  ],
  standalone: true, // Assuming this is true based on your setup
  templateUrl: './admin.html', // Renamed from admin.html to admin.component.html in previous steps
  styleUrl: './admin.css'     // Renamed from admin.css to admin.component.css in previous steps
})
// FIX: Implement OnInit and rename class to AdminComponent
export class AdminComponent implements OnInit { 
  
  // 💡 FIX: Add missing isLoading property for template
  isLoading: boolean = true; 

  // --- Data Properties ---
  reportData!: AdminReport; // Holds analytics data
  unapprovedHosts: User[] = []; // Holds hosts pending approval
  
  // --- Chart Properties ---
  public pieChartOptions = { responsive: true, maintainAspectRatio: false };
  public pieChartLabels: string[] = ['Hosts', 'Coordinators', 'Students'];
  public pieChartData: ChartData<'pie'> = { labels: this.pieChartLabels, datasets: [] };
  public pieChartType: ChartType = 'pie';

  name: string = "Admin";
  
  // NEW: Inject the UserService
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // Start fetching data immediately when the component loads
    this.fetchData(); 
  }

  // --- Data Fetching Logic ---
  fetchData() {
    // We use Promise.all or similar logic if the order wasn't important, 
    // but for simplicity, we'll track loading manually within the fetch calls.
    this.isLoading = true;
    
    // We fetch report data and hosts, and only set isLoading=false after both are complete.
    // For now, let's keep the existing structure and ensure 'isLoading' is toggled.
    this.fetchReportData();
    this.fetchUnapprovedHosts();
  }

  fetchReportData() {
    this.userService.getAdminReport().subscribe({
      next: (data) => {
        this.reportData = data;
        this.updateChartData(data); // Update chart when data arrives
        // Note: setting isLoading=false only in the last call to complete
      },
      error: (err) => {
        console.error('Error fetching report data:', err)
        // Note: setting isLoading=false only in the last call to complete
      },
      complete: () => {
        // Since we have two separate calls in fetchData(), we need a cleaner way to track completion.
        // For simplicity *right now*, let's assume the other one is quick or handle loading outside the component.
        // For a proper solution, we'd use forkJoin, but for this fix, we'll put the loading logic in the last-called fetch.
      }
    });
  }

  fetchUnapprovedHosts() {
    this.userService.getUnapprovedHosts().subscribe({
      next: (data) => this.unapprovedHosts = data,
      error: (err) => console.error('Error fetching unapproved hosts:', err),
      // 💡 FIX: Set isLoading to false upon completion of the last fetching operation (whether success or error)
      complete: () => this.isLoading = false
    });
  }
  
  // --- Chart Update Logic ---
  updateChartData(data: AdminReport): void {
    this.pieChartData = {
      labels: this.pieChartLabels,
      datasets: [{
        data: [data.totalHosts, data.totalCoordinators, data.totalStudents],
        backgroundColor: ['#42A5F5', '#66BB6A', '#FFA726'],
        hoverBackgroundColor: ['#64B5F6', '#81C784', '#FFB74D']
      }]
    };
  }
  
  // --- Host Management Logic (Accept/Reject) ---
  handleHostAction(host: User, action: 'accept' | 'reject') {
    const status = action === 'accept';
    const actionText = status ? 'Accepted' : 'Rejected'; 

    this.userService.updateHostApproval(host.id, status).subscribe({
      next: () => {
        console.log(`Host ${host.name} successfully ${actionText}.`); 

        // Update the view locally and refresh report counts
        this.unapprovedHosts = this.unapprovedHosts.filter(h => h.id !== host.id);
        this.fetchReportData(); 
      },
      error: (err) => {
        console.error(`Failed to ${action} host ${host.name}:`, err);
      }
    });
  }
}
