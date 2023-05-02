import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { user } from 'app/models/user';
import { UsersService } from 'app/service/UserService';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: user;
  id: number;
  constructor(private us:UsersService,private router: Router) { }
  
  ngOnInit(): void {
    this.us.getUserById(parseFloat(localStorage.getItem('currentUser'))).subscribe(
      (data) => {
        this.user = data;
      },
      (error) => {
        console.log(error);
      }
    
    );
  }
  updateUser() {
    console.log(parseFloat(localStorage.getItem('currentUser')));
    this.us.updateUser(parseFloat(localStorage.getItem('currentUser')), this.user)
      .subscribe(
        updatedUser => {
          console.error('Profile modifié');
          window.location.reload();
          this.router.navigate(['profile']);

        },
        error => {
          console.error(error);
        }
      );
  }
  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
