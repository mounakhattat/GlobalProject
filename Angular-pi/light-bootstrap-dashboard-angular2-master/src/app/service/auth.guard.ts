import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsersService } from './UserService';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private us:UsersService, private router :Router){ }
  canActivate(){


    if (this.us.isLoggedIn()){
        return true;
      }
    this.router.navigate(['login']);
    return false;
    
    }

  
}
