import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-footer',
  imports: [RouterLink,RouterOutlet],
  templateUrl: './footer.html',
  styleUrl: './footer.css'
})
export class Footer {

}
