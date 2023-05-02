import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'Services/EventService/event.service';
import { Event } from 'models/Event';
@Component({
  selector: 'app-update-event',
  templateUrl: './update-event.component.html',
  styleUrls: ['./update-event.component.scss']
})
export class UpdateEventComponent implements OnInit {

  submitted = false;
  event:Event;
  id!: number;
  successMessage: string = '';

  enumKeys:any[]=[];
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
    private fb:FormBuilder,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.event = history.state.event;
    this.id=this.event.id_Event
    this.reactiveForm = this.fb.group({
      name:[this.event.name, [Validators.required, Validators.minLength(3)]],
      date: [this.event.date+""],
      startTime: [this.event.startTime+""],
      endTime: [this.event.endTime+""],
      description:[this.event.description+"", [Validators.required, Validators.minLength(3)]],
      venue:[this.event.venue+"", [Validators.required, Validators.minLength(3)]],
      organizer:[this.event.organizer+"", [Validators.required, Validators.minLength(3)]],
      ticketPrice:[this.event.ticketPrice+"", [Validators.required]],
      ticketAvailability:[this.event.ticketAvailability+"", [Validators.required]],
      contactInformation:[this.event.contactInformation+"", [Validators.required, Validators.minLength(3)]],
      formLink : [this.event.formLink+"", [Validators.required, Validators.minLength(3)]],
      });
  
  }
  Save() {
    let event = this.reactiveForm.getRawValue();
    this.eventService
    .updateEvent(this.id,event).subscribe(data => {
      console.log(data);
         // Set the success message
  this.successMessage = 'Error:Check The Form!';

  // Display the alert
  alert(this.successMessage);
    }, 
    error => console.log(error));
       // Set the success message
  this.successMessage = 'Event added successfully!';

  // Display the alert
  alert(this.successMessage);
  }

  onSubmit() {
    this.submitted = true;
    this.Save();    
    this.gotoList;
  }

  gotoList() {
    this.router.navigate(['/list-event']);
  }


}