import { ReclamationService } from 'Services/ReclamationService/reclamation.service';
import { Reclamation } from 'models/Reclamation';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TypeReclamation } from 'models/enums/TypeReclamation';
import { SMSService } from 'Services/SMSService/sms.service';
@Component({
  selector: 'app-create-reclamation',
  templateUrl: './create-reclamation.component.html',
  styleUrls: ['./create-reclamation.component.scss']
})

export class CreateReclamationComponent implements OnInit {

  //reclamation: Reclamation = new Reclamation();
  successMessage: string = '';
  submitted = false;
  optionValues =TypeReclamation ;
  enumKeys:any[]=[];
  reactiveForm = this.fb.group({
    title:['', [Validators.required, Validators.minLength(3)]],
    content: ['',Validators.required],
    dateReclamation: [''],
    type: [''],
    });
  constructor(private reclamationService:ReclamationService ,
    private router: Router,
    private fb:FormBuilder,
    private smsService: SMSService,
    ) { }

  ngOnInit() {
    this.enumKeys = Object.keys(this.optionValues).filter(String);

    
  }

  newReclamation(): void {
  }

  Save() {
    let reclamation = this.reactiveForm.getRawValue();
    console.log(reclamation);
    this.reclamationService
    .createReclamation(reclamation).subscribe(data => {
      console.log(data);
      // Set the success message
  this.successMessage = 'Event added successfully!';

  // Display the alert
  alert(this.successMessage);
    }, 
    error => console.log(error));
      // Set the success message
  this.successMessage = 'Error:Check The Form!';

  // Display the alert
  alert(this.successMessage);
  }

  onSubmit() {

    this.submitted = true;
    this.Save();    
  }
  sendSMS() {
    if (this.reactiveForm.valid) {
      const reclamation = this.reactiveForm.value;
      const destinationSMSNumber = '+216 25131887';
      const smsMessage = `New Reclamation: ${reclamation.type} - ${reclamation.content}`;
      this.smsService.sendSMS(destinationSMSNumber, smsMessage).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
          // Handle error here
        }
      );
    }
  }
  
  gotoList() {
    this.router.navigate(['/reclamations']);
  }
}
