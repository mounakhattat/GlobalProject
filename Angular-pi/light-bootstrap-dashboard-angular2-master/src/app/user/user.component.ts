import { Component, OnInit } from '@angular/core';
import { UsersService } from '../service/UserService';
import { user } from 'app/models/user';
@Component({
selector: 'app-user',
templateUrl: './user.component.html',
styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  days:number;
  score:number=0;
  user:any;
  listUsers : string[];
constructor(private _service:UsersService) { }
ngOnInit(): void {

this._service.getUsersName().subscribe(res=>{console.log(res);

this.listUsers=res}
);
this.calculateScore(this.user);
}
deleteUser(user:user) {
    
    
  this._service.deleteUser(user.idUser).subscribe(
    user => {
      
      // Do any additional handling of the response here
      window.location.reload();
    },
    );

}
  downloadPdf() {
    this._service.downloadPdf().subscribe(data => {
      const blob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'TableOfUsers.pdf';
      link.click();
      window.URL.revokeObjectURL(url);
      link.remove();
    });
  }
  exportToExcel() {
    this._service.exportToExcel().subscribe(blob => {
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = 'Users.xlsx';
      link.click();
    });
  }
  
  banUser(user:user) {
    this._service.banUser(user.idUser,30)
      .subscribe(
        user => {
          console.log(`User ${user.idUser} has been banned for ${this.days} days`);
          // Do any additional handling of the response here
          window.location.reload();
        },
  
      );
  
  }
  

  calculateScore(user:user) {
    this.score=0;

    if (user.age >= 18 && user.age <= 25) {
      this.score += 5;
    } else if (user.age > 25 && user.age <= 40) {
      this.score += 10;
    } else if (user.age > 40) {
      this.score += 15;
    }

    if (user.job === 'teacher') {
      this.score += 5;
    } else if (user.job === 'engineer') {
      this.score += 10;
    } else if (user.job === 'doctor') {
      this.score += 15;
    }
    return this.score;
    
  }
  
 
}
  
    
