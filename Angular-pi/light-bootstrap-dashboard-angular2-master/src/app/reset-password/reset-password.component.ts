import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, Validators} from "@angular/forms";
import { UsersService } from 'app/service/UserService';
import { user } from 'app/models/user';





@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss'],
 
})
export class ResetPasswordComponent implements OnInit {
  email: string;
  token: string;
  passwordConfirm: string;
  passmatch:boolean=false;

  PassMatch:String="the passwords does not match"
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  errorMessage: string;
  user: user;

  constructor( private service: UsersService,private router: Router) { }

  ngOnInit(): void {
  }
  
 
  generatePassToken() {
    this.service.generatePassToken(this.email)
      .subscribe(data => {
        this.router.navigate(['resetPassToken']);
        this.token = data.token;
      });
  }

 



}
