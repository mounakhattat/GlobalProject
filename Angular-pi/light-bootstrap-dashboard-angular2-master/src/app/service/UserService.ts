import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { user } from 'app/models/user';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
providedIn: 'root'                
})
export class UsersService {
usersUrl="http://localhost:8083/user/retrieve-user";
constructor(private _http:HttpClient,
  private router: Router
  )
  
 {     
}
getUsersName() : Observable<string[]> {
return this._http.get<string[]>(this.usersUrl);
}
API_URL="http://localhost:8083/user/get-user"
getUserById(id: any): Observable<user> {
  return this._http.get<user>(`${this.API_URL}/${id}`);
  
}
signup(username: string, email: string, password: string): Observable<any> {
    const body = { username, email, password };
 
    return this._http.post<user>("http://localhost:8083/api/auth/signup",body);
    }

    login(username: string, password: string): Observable<any> {
      return this._http.post<any>("http://localhost:8083/api/auth/signin", { username: username, password: password })
      .pipe(
        tap(response => {
          // Store user data in local storage
          localStorage.setItem('currentUser', JSON.stringify(response.id));
        
          localStorage.setItem('accessToken', response.accessToken);
          localStorage.setItem('role', response.roles);
  
          localStorage.setItem('id', response.id);
          const token = localStorage.getItem('accessToken');

          localStorage.setItem('accessToken', token);

        })
      );
  
  
      
    }
        
  generatePassToken(email: any): Observable<any> {
    const url = `http://localhost:8083/api/auth/resetPasswordRequest/${email}`;
    return this._http.get<any>(url);
  }
   
  resetPassword(token: string, password: string): Observable<any> {
    const url = `http://localhost:8083/api/auth/resetPassword/${token}/${password}`;
    return this._http.get<any>(url);
  }
  private API_URL1 = 'http://localhost:8083/user';
 
 
  deleteUser(idUser: number): Observable<any> {
    return this._http.delete(`${this.API_URL1}/delete/${idUser}`);
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('accessToken');
  }

  private baseUrl = 'http://localhost:8083/user';


  downloadPdf(): Observable<Blob> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this._http.get<Blob>(`${this.baseUrl}/PDF`, { headers, responseType: 'blob' as 'json' });
  }
  exportToExcel() {
    return this._http.get('http://localhost:8083/user/export/excel', { responseType: 'blob' });
  }
  banUser(idUser: number, days: number): Observable<user> {
    const url = `http://localhost:8083/user/banUser/${idUser}/${days}`;
    return this._http.get<user>(url);
  }
  parseJwt(){
    const jwtHelper = new JwtHelperService();
    const objJwt= jwtHelper.decodeToken(localStorage.getItem('accessToken')!);
    const token = localStorage.getItem('accessToken');
    localStorage.setItem('accessToken', token);

    localStorage.setItem('idUser',objJwt.id)
    console.log(objJwt);
    if(objJwt.role=='ROLE_VISITOR' || objJwt.role=='ROLE_CUSTOMER'){
      this.router.navigate(['profile']);
    }
    else{
      this.router.navigate(['dashboard'])
    }

   }
   private apiUrl = 'http://localhost:8083/api/auth';
   confirmCode(code: any): Observable<any> {
    const url = `${this.apiUrl}/confirm-code/${code}`;
    return this._http.get<any>(url);
  }
  updateUser(id: any, user: user): Observable<user> {
    const url = `http://localhost:8083/user/update/${id}`;
    return this._http.put<user>(url, user);
  }
 
}
