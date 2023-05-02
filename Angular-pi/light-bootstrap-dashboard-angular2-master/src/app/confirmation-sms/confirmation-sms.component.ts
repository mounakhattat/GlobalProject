import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsersService } from 'app/service/UserService';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-confirmation-sms',
  templateUrl: './confirmation-sms.component.html',
  styleUrls: ['./confirmation-sms.component.scss']
})
export class ConfirmationSMSComponent implements OnInit {
   code: any ;

  constructor(private userService: UsersService,private router: Router) {}
  ngOnInit(): void {

  }

  confirmCode(code: any) {
    this.userService.confirmCode(code).subscribe(data => {
        this.router.navigate(['login']);

      },
      error => {
        console.log('Erreur lors de la confirmation: ', error);
      }
    );
  }}