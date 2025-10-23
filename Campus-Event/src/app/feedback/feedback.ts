import { HttpClientModule } from '@angular/common/http';
import { Component, VERSION } from '@angular/core';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-feedback',
  imports: [NgbRatingModule,HttpClientModule],
  templateUrl: './feedback.html',
  styleUrl: './feedback.css'
})
export class Feedback{
isLoading: boolean | undefined;
handleSubmit() {
throw new Error('Method not implemented.');
}
title = "Event-Rating";
name = "Give Event Rating";
currentRate= 0;


}
