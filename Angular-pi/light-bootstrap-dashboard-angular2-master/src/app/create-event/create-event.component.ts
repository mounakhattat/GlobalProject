import { Component, OnInit } from '@angular/core';
import { Event } from 'models/Event';
import { EventService } from 'Services/EventService/event.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {
  minDate:String;
  successMessage: string = '';
  submitted = false;  
  reactiveForm = this.fb.group({
    name:['', [Validators.required, Validators.minLength(3)]],
    date: [''],
    startTime: [''],
    endTime: [''],
    description:['', [Validators.required, Validators.minLength(3)]],
    venue:['', [Validators.required, Validators.minLength(3)]],
    organizer:['', [Validators.required, Validators.minLength(3)]],
    ticketPrice:['', [Validators.required]],
    ticketAvailability:['', [Validators.required]],
    contactInformation:['', [Validators.required, Validators.minLength(3)]],
    formLink : ['', [Validators.required, Validators.minLength(3)]],


    });

  constructor(private eventService:EventService ,
    private router: Router,
    private fb:FormBuilder,) { }

  ngOnInit(): void {
    const today = new Date();
    const yyyy = today.getFullYear();
    let mm: string | number = today.getMonth() + 1; // January is 0
    let dd: string | number = today.getDate();
  
    if (mm < 10) {
      mm = `0${mm}`;
    }
  
    if (dd < 10) {
      dd = `0${dd}`;
    }
  
    this.minDate = `${yyyy}-${mm}-${dd}`;
  }


  newEvent(): void {
  }
  Save() {
    let event = this.reactiveForm.getRawValue();
    event.startTime=event.startTime+":00";
    event.endTime=event.endTime+":00";
    console.log(event);
    this.eventService
    .createEvent(event).subscribe(data => {
      console.log(data);
       // Set the success message
  this.successMessage = 'Event added successfully!';

  // Display the alert
  alert(this.successMessage);
    }, 
    error => console.log(error));
    // Set the success message
  this.successMessage = 'Error check the form!';

  // Display the alert
  alert(this.successMessage);
  }
  onSubmit() {

    this.submitted = true;
    this.Save();    
  }
 /* gotoList() {
    this.router.navigate(['/Events']);
  }
  */
}


