import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { EventDetailComponent } from './sportclub-events/event-detail/event-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import {AttendingModalComponent} from "./sportclub-events/attending-modal/attending-modal.component";
import {EventListParticipantsComponent} from "./sportclub-events/event-list-participants/event-list-participants.component";
import {EventListParticipantsService} from "./sportclub-events/service/event-list-participants.service";
import {SportClubEventService} from "./sportclub-events/service/sportclub-event.service";

@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    EventDetailComponent,
    AttendingModalComponent,
    EventListParticipantsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    SportClubUserManagementModule,
    SportClubEventsModule,FormsModule,ReactiveFormsModule
  ],
  bootstrap: [AppComponent],
  providers: [
    SportClubEventService,
    EventListParticipantsService
  ]
})
export class AppModule {
}
