import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-gallery',
  imports: [RouterLink,RouterOutlet],
  templateUrl: './gallery.html',
  styleUrl: './gallery.css'
})
export class Gallery {

}
