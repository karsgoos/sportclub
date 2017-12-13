import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEventsComponent} from './sportclub-events.component';
import { FullCalendarModule } from 'ng-fullcalendar';
import { EventCalendarComponent } from './event-calendar/event-calendar.component';


@NgModule({
  imports: [
    CommonModule,
    FullCalendarModule
  ],
  declarations: [SportClubEventsComponent, EventCalendarComponent],
  providers: [SportClubEventService]
})
export class SportClubEventsModule {
}
