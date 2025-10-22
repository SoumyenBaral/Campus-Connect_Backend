import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterOutlet } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact-us',
  imports: [Footer,RouterOutlet,CommonModule, FormsModule, HttpClientModule],
  templateUrl: './contact-us.html',
  styleUrl: './contact-us.css'
})
export class ContactUs {
submitEvent() {
throw new Error('Method not implemented.');
}
constructor(private http: HttpClient) {
    // this.getdata();
  }

userData = {
    "name": "",
    "email": "",
    "message": ""
  }
 adddata() {
    this.http.post("http://localhost:8080/api/postcontact", this.userData).subscribe({
      next: (res) => console.log('Contact submitted:', res),
      error: (err) => console.error('Submission failed:', err)
    });
  }


}
