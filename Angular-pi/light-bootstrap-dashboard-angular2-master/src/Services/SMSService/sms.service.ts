import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SMSService {
  private apiUrl = 'http://localhost:8080/SpringTest/api/v1/PostSMS';

  constructor(private http: HttpClient) { }

  sendSMS(destinationSMSNumber: string, smsMessage: string) {
    const sendRequest = {
      destinationSMSNumber,
      smsMessage
    };
    return this.http.post(this.apiUrl, sendRequest);
  }
}
