import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEventsComponent} from './sportclub-events.component';
import {SportclubEventCreationComponent} from "./components/sportclub-event-creation/sportclub-event-creation.component";


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [SportClubEventsComponent,
        SportclubEventCreationComponent],
  providers: [SportClubEventService]
})
export class SportClubEventsModule {
}
