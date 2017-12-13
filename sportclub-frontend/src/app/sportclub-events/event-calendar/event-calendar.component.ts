import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import { CalendarComponent } from "ng-fullcalendar";
import { Options } from "fullcalendar";
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {SportClubEventService} from "../service/sportclub-event.service";

@Component({
  selector: 'app-event-calendar',
  templateUrl: './event-calendar.component.html',
  styleUrls: ['./event-calendar.component.css']
})
export class EventCalendarComponent implements OnInit {

  //@Output() displayEvent = new EventEmitter<SportClubEvent>();
  displayEvent: any;

  events: SportClubEvent[];

  calendarOptions: Options;
  @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
  constructor(private eventService: SportClubEventService) {}
  ngOnInit() {
    // this.eventService.getEvents().subscribe(events => this.events = events);
    this.calendarOptions = {
      locale: 'nl-be',
      today: 'Vandaag',
      editable: false,
      eventLimit: false,
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
      },
      events: [{
        title: 'event',
        start: '2017-12-12T22:00',
        end: '2017-12-12T23:00'
      }]
    };

  }


  eventClick(model: any) {
    model = {
      event: {
        id: model.event.id,
        start: model.event.start,
        end: model.event.end,
        title: model.event.title,
        allDay: model.event.allDay
        // other params
      },
      duration: {}
    };
    this.displayEvent = model;
  }

}
