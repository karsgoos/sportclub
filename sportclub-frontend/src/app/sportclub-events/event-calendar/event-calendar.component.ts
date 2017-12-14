import {Component, Input, OnInit, ViewChild} from '@angular/core';
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
  // displayEvent: any;
  // @Input() events: SportClubEvent[];
  calendarOptions: Options;
  @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;

  constructor(private eventService: SportClubEventService) {}

  ngOnInit(){
    this.eventService.getEvents().subscribe(events =>{
      this.calendarOptions = {
        locale: 'nl-be',
        buttonText:{
          today:    'Vandaag',
          month:    'Maand',
          week:     'Week',
          day:      'Dag',
          list:     'Agenda'
        },
        timeFormat: 'H:mm',
        displayEventTime: true,
        displayEventEnd: true,
        height: 'parent',
        slotLabelFormat: 'H:mm',
        contentHeight: 'auto',
        editable: false,
        eventLimit: false,

        //tranforms the input from the webservice to an object that can be used in a calendar
        eventDataTransform: eventData => {
          let event = Object.assign({},eventData);
          event.title = event.name;
          event.start = event.startDate;
          event.end = event.endDate;
          event.url = '/event-detail/'+event.id;
          event.color = event.closed ? "blue" : "orange";
          return event;
        },

        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay,listMonth'
        },
        events: events
      };
    } );

  }

}
