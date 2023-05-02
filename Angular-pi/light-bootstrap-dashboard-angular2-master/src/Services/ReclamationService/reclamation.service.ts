import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {

  private baseUrl = 'http://localhost:8080/SpringTest/Reclamation/';

  constructor(private http: HttpClient) { }

  getReclamation(idReclamation: number): Observable<any> {
    return this.http.get(`${this.baseUrl+'getOneReclamation'}/${idReclamation}`);
  }

  createReclamation(reclamation: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl+"addReclamation"}`, reclamation);
  }

  updateReclamation(idReclamation: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl+'updateRec'}/${idReclamation}`, value);
  }

  deleteReclamation(idReclamation: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+'Reclamation'}/${idReclamation}`, { responseType: 'text' });
  }

  getReclamationsList(): Observable<any> {
    return this.http.get(`${this.baseUrl+"listReclamation"}`);
  }
}