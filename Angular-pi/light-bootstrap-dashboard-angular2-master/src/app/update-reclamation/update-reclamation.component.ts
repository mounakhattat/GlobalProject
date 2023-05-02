import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReclamationService } from 'Services/ReclamationService/reclamation.service';
import { Reclamation } from 'models/Reclamation';
import { StateReclamation } from 'models/enums/StateReclamation';
import { TypeReclamation } from 'models/enums/TypeReclamation';

@Component({
  selector: 'app-update-reclamation',
  templateUrl: './update-reclamation.component.html',
  styleUrls: ['./update-reclamation.component.scss']
})
export class UpdateReclamationComponent implements OnInit {
  submitted = false;
  successMessage: string = '';
  reclamation:Reclamation;
  optionValues =StateReclamation ;
  enumKeys:any[]=[];
  reactiveForm = this.fb.group({
    title:['', [Validators.required, Validators.minLength(3)]],
    content: ['',Validators.required],
    dateReclamation: [''],
    type: [''],
    state : [''],
    });

  constructor(private reclamationService:ReclamationService ,
    private router: Router,
    private fb:FormBuilder,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.enumKeys = Object.keys(this.optionValues).filter(String);
    this.reclamation = history.state.reclamation;
    console.log(this.reclamation);
    this.reactiveForm = this.fb.group({
      title:[this.reclamation.title, [Validators.required, Validators.minLength(3)]],
      content: [this.reclamation.content,Validators.required],
      dateReclamation: [""],
      type: [this.reclamation.type+""],
      state : [''+StateReclamation[this.reclamation.state]],
      });
  
  }
  Save() {
    let reclamation = this.reactiveForm.getRawValue();
    console.log(reclamation);
    this.reclamationService
    .updateReclamation(this.reclamation.idReclamation,reclamation).subscribe(data => {
      console.log(data);
  this.successMessage = 'Error Check The form!';
  alert(this.successMessage);
    }, 
    error => console.log(error));
  this.successMessage = 'Event added successfully!';
  alert(this.successMessage);
  }

  onSubmit() {
    this.submitted = true;
    this.Save();    
    this.gotoList;
  }

  gotoList() {
    this.router.navigate(['/list-reclamation']);
  }

}
