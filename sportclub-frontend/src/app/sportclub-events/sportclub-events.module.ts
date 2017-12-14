import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEventsComponent} from './sportclub-events.component';
import { FullCalendarModule } from 'ng-fullcalendar';
import { EventCalendarComponent } from './event-calendar/event-calendar.component';

import {SportclubEventCreationComponent} from "./components/sportclub-event-creation/sportclub-event-creation.component";
import {ReactiveFormsModule,FormsModule} from '@angular/forms';
import {AngularDateTimePickerModule} from "angular2-datetimepicker";


@NgModule({
  imports: [
    CommonModule,
    FullCalendarModule,
    ReactiveFormsModule,
    AngularDateTimePickerModule
  ],
  declarations: [SportClubEventsComponent,
        SportclubEventCreationComponent, EventCalendarComponent],
  providers: [SportClubEventService]
})
export class SportClubEventsModule {
}
