import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  url : string = 'http://localhost:8083/api/auth/';



  constructor(private http: HttpClient) { }
  login( username, pass): Observable<any> {

    return this.http.get(this.url+"signin/",{params:{username:username,pass:pass}});
  }

}
