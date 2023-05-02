import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl = 'http://localhost:8080/SpringTest/Events/';
  constructor(private http: HttpClient) { }
  
 
  createEvent(event: Object): Observable<Object> {
    console.log("here");
    console.log(event);
    return this.http.post(`${this.baseUrl+"addEvents"}`, event);
  }

  updateEvent(Id_Event: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl+'updateEvent'}/${Id_Event}`, value);
  }

  deleteEvent(Id_Event: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+'DeleteEvents'}/${Id_Event}`, { responseType: 'text' });
  }

  getEventList(): Observable<any> {
    return this.http.get(`${this.baseUrl+"listEvents"}`);
  }
  
  

}