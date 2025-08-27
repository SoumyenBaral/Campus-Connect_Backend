import { Component } from '@angular/core';
import { Footer } from '../footer/footer';
import { Login } from "../login/login";

@Component({
  selector: 'app-host',
  imports: [Footer, Login],
  templateUrl: './host.html',
  styleUrl: './host.css'
})
export class Host {

}
