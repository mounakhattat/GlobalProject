import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
    private baseUrl = 'http://localhost:9000';
    constructor(private http: HttpClient) { }

    getEventList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
      }
}