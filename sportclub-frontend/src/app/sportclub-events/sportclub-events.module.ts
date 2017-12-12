import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEventsComponent} from './sportclub-events.component';

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [SportClubEventsComponent],
  providers: [SportClubEventService]
})
export class SportClubEventsModule {
}
