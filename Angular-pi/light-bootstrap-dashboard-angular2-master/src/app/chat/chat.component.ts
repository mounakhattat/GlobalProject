import { Component, OnInit } from '@angular/core';
import { ChatService } from 'Services/ChatService/Chat.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  data: any;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:9000').subscribe(data => {
      this.data = data;
    });
  }
  }
