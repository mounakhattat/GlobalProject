import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UsersService } from 'app/service/UserService';


@Component({
  selector: 'app-reset-password-token',
  templateUrl: './reset-password-token.component.html',
  styleUrls: ['./reset-password-token.component.scss']
})
export class ResetPasswordTokenComponent implements OnInit {
  email: string;
  token: string;
 password: string;
  passwordConfirm: string;
  errorMessage: string;


  constructor( private service: UsersService,private router: Router) { }

  ngOnInit(): void {
  }
  resetPassword() {
    console.log("token: ", this.token);
  console.log("password: ", this.password);
  this.service.resetPassword(this.token, this.password)
    .subscribe();
    this.router.navigate(['profile']);

}}
