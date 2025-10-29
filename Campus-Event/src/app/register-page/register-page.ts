import { Component } from '@angular/core';
import { Footer } from "../footer/footer";
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-register-page',
  imports: [Footer,FormsModule],
  templateUrl: './register-page.html',
  styleUrl: './register-page.css'
})
export class RegisterPage {
http: any;
constructor(http:HttpClient){

}

userdata={
  "fullName":"",
  "emailAddress":"",
  "mobileNumber":"",
  "organization":""
}


adduserdetails(){
  this.http.post("http://localhost:8080/api/postuser").this.userdata.subscribe({
       next: (res: any) => console.log('Event registered  successful:', res),
      error: (err:any) => console.error('Submission failed:', err)
  })
}
}

