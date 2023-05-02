import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'models/Event';
import { EventService } from 'Services/EventService/event.service';
import jsPDF from 'jspdf';
import qrcode = require('qrcode');

@Component({
  selector: 'app-list-event',
  templateUrl: './list-event.component.html',
  styleUrls: ['./list-event.component.scss']
})
export class ListEventComponent implements OnInit {
  EventList:Event[]=[];
  searchTerm: string = '';
  searchKeyword: string = '';
  constructor(private EventS:EventService,
    private router: Router) { }

  ngOnInit(): void {
    this.getEvent();
    console.log("here");
  }
  getEvent(){
    this.EventS.getEventList().subscribe(data => {
      this.EventList= data;
      console.log(this.EventList);
    });
  }
  deleteEvent(id:any){
    this.EventS.deleteEvent(Number(id)).subscribe( () =>this.getEvent() );
  }
  navigateToUpdateEvent(event : Event){
    this.router.navigate(['/update-event'], { state: { event: event }});
  }
  navigateTocreateEvent(){
    this.router.navigate(['/create-event']);
  }
  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString();
    return `${day}-${month}-${year}`;
  }
  sortEventsByDateASC() {
    this.EventList.sort((a, b) => {
      return new Date(a.date).getTime() - new Date(b.date).getTime();
    });
  }
  sortEventsDesc() {
    this.EventList.sort((a, b) => {
      return new Date(b.date).getTime() - new Date(a.date).getTime();
    });
  }
  async generatePdf(event: Event): Promise<void> {
    if (!event) {
      console.log('Event object is null or undefined');
      return;
    }
  
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.width;
    const pageHeight = doc.internal.pageSize.height;
    const margin = 20;
    let currentY = margin;
  
    // Add header
    doc.setFontSize(30);
    doc.text(`Event Details`, margin, currentY);
    currentY += 20;
  
    // Add line separator
    doc.setLineWidth(0.5);
    doc.line(margin, currentY, pageWidth - margin, currentY);
    currentY += 10;
  
    // Add event details
    doc.setFontSize(14);
    doc.text(`Event ID: ${event.id_Event ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Event Name: ${event.name ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Venue: ${event.venue ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Organizer: ${event.organizer ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Date: ${event.date ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Time: ${event.startTime ?? ''} - ${event.endTime ?? ''}`, margin, currentY);
    currentY += 10;
  
    // Generate QR code for formLink
    const qrCodeSize = 100;
    const qrCodeMargin = 10;
    const qrCodeX = pageWidth - margin - qrCodeSize - qrCodeMargin;
    const qrCodeY = currentY;
    const qrCodeData = event.formLink ?? '';
    const qrCodeOptions = {
      errorCorrectionLevel: 'M',
      type: 'image/jpeg',
      rendererOpts: {
        quality: 0.3,
        backgroundColor: '#ffffff',
      },
    };
    const qrCodeDataURL = await qrcode.toDataURL(qrCodeData, qrCodeOptions);
    doc.addImage(qrCodeDataURL, 'JPEG', qrCodeX, qrCodeY, qrCodeSize, qrCodeSize);
  
    doc.text(`Registration: ${event.formLink ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Description: ${event.description ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Ticket Price: ${event.ticketPrice ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Ticket Availability: ${event.ticketAvailability ?? ''}`, margin, currentY);
    currentY += 10;
    doc.text(`Contact Information: ${event.contactInformation ?? ''}`, margin, currentY);
  
    // Save the PDF document
    doc.save(`${event.name ?? 'event'}.pdf`);
  }
  
}
 