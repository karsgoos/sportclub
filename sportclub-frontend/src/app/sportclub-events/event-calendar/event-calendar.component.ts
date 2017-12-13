import {Component, EventEmitter, Input, OnChanges, OnInit, Output, ViewChild} from '@angular/core';
import { CalendarComponent } from "ng-fullcalendar";
import { Options } from "fullcalendar";
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {SportClubEventService} from "../service/sportclub-event.service";

@Component({
  selector: 'app-event-calendar',
  templateUrl: './event-calendar.component.html',
  styleUrls: ['./event-calendar.component.css']
})
export class EventCalendarComponent implements OnChanges {


  //@Output() displayEvent = new EventEmitter<SportClubEvent>();
  displayEvent: any;

  @Input() events: SportClubEvent[];

  calendarOptions: Options;
  @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
  constructor(private eventService: SportClubEventService) {}
  ngOnChanges() {
    this.calendarOptions = {
      locale: 'nl-be',
      today: 'Vandaag',
      editable: false,
      eventLimit: false,

      //tranforms the input from the webservice to an object that can be used in a calendar
      eventDataTransform: eventData => {
        let event = Object.assign({},eventData);
        event.title = event.name;
        event.start = event.startDate;
        event.end = event.endDate;
        event.url = '/eventDetail/'+event.id;
        return event;
      },

      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
      },
      events: this.events
    };

  }


  // eventClick(model: any) {
  //   model = {
  //     event: {
  //       id: model.event.id,
  //       start: model.event.start,
  //       end: model.event.end,
  //       title: model.event.title,
  //       allDay: model.event.allDay
  //       // other params
  //     },
  //     duration: {}
  //   };
  //   this.displayEvent = model;
  // }

}
