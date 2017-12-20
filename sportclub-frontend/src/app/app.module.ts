import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';

import {EventDetailComponent} from './sportclub-events/event-detail/event-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AttendingModalComponent} from "./sportclub-events/attending-modal/attending-modal.component";
import {DeleteModalComponent} from "./sportclub-events/delete-modal/delete-modal.component";
import {EventListParticipantsComponent} from "./sportclub-events/event-list-participants/event-list-participants.component";
import {EventListParticipantsService} from "./sportclub-events/service/event-list-participants.service";
import {SportClubEventService} from "./sportclub-events/service/sportclub-event.service";
import {CancellationsModalComponent} from './sportclub-events/cancellations-modal/cancellations-modal.component';
import {RoleManagementComponent} from './role-management/role-management.component';
import {MenuBarComponent} from './menu-bar/menu-bar.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginModule} from './login/login.module';
import {PointsComponent} from './points/points.component';
import {AuthInterceptor} from './login/services/auth.interceptor';
import {AuthenticationService} from './login/services';
import {RegistrationComponent} from './registration/registration.component';
import {RegisteredUserGuard} from './guard/registered-user.guard';
import {ModeratorGuard} from "./guard/moderator.guard";
import {Config} from './common/config';

@NgModule({
  declarations: [
    AppComponent,
    RoleManagementComponent,
    MenuBarComponent,
    MenuBarComponent,
    EventDetailComponent,
    AttendingModalComponent,
    DeleteModalComponent,
    AttendingModalComponent,
    EventListParticipantsComponent,
    CancellationsModalComponent,
    PointsComponent,
    MenuBarComponent,
    RegistrationComponent
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
    RegisteredUserGuard,
    ModeratorGuard,
    AuthenticationService,
    SportClubEventService,
    EventListParticipantsService,
    AuthenticationService,
    Config],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    AppRoutingModule,
    SportClubUserManagementModule,
    SportClubEventsModule,
    ReactiveFormsModule,
    FormsModule
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
