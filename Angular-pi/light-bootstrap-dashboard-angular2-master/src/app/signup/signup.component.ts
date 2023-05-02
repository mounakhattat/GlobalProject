import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from '../service/UserService';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  invalidLogin: boolean; 
  form: FormGroup;
  error: string;
  constructor( private fb: FormBuilder,private router: Router, 
    private us: UsersService) { 

      this.form = this.fb.group({
        username: ['', Validators.required],
        
        email: ['', Validators.required],
       
        password: ['', Validators.required]
      });
    }

  ngOnInit(): void {
  }
  onSubmit(): void {
    const { username,email , password} = this.form.value;
    this.us.signup(username,email, password)
      .subscribe(
        () => {
          this.router.navigate(['confirmationSMS']);
        },
        (error) => {
          this.error = error.error.message;
        }
      );
  }

}
