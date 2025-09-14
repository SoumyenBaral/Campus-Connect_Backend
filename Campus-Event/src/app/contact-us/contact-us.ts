import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-contact-us',
  imports: [Footer,RouterOutlet],
  templateUrl: './contact-us.html',
  styleUrl: './contact-us.css'
})
export class ContactUs {

}
