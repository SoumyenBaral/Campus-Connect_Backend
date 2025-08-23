import { Component, VERSION } from '@angular/core';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-feedback',
  imports: [NgbRatingModule],
  templateUrl: './feedback.html',
  styleUrl: './feedback.css'
})
export class Feedback {
title = "Event-Rating";
name = "Give Event Rating";
currentRate= 0;
}
