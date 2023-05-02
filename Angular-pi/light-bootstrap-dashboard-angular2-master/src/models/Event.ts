import { Time } from "@angular/common";
export class Event{
    id_Event:number;
    name:string;
    date:Date;
    startTime : Time;
    endTime : Time;
    venue : String;
    description:String;
    organizer:String;
    ticketPrice: number;
    ticketAvailability : number;
    contactInformation: String;
    formLink : String;
}
