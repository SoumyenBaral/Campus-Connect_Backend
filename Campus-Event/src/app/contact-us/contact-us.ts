import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterOutlet } from '@angular/router';
import { Invitation } from '../invitation/invitation';


@Component({
  selector: 'app-contact-us',
  imports: [Footer,RouterOutlet,Invitation],
  templateUrl: './contact-us.html',
  styleUrl: './contact-us.css'
})
export class ContactUs {

}
