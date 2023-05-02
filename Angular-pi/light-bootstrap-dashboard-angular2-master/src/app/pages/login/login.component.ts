import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UsersService } from 'app/service/UserService';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  errorMessage: string;

  constructor(
    private usersService: UsersService,
    private router: Router
  ) { }

  login() {
    this.usersService.login(this.username, this.password)
      .subscribe(
        data =>
         {
           
      localStorage.setItem('token',data.token);
      this.usersService.parseJwt();
        },
        error => {
          this.errorMessage = "Bad credentials";

        }
         
    
        
      );
  }

  ngOnInit(): void {
    localStorage.removeItem('currentUser'); 
    localStorage.removeItem('accessToken');
    localStorage.removeItem('role');
    localStorage.removeItem('token');
  }

}
