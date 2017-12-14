import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {SportclubEventCreationComponent} from './sportclub-events/components/sportclub-event-creation/sportclub-event-creation.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MenuBarComponent} from "./menu-bar/menu-bar.component";

@NgModule({
  declarations: [
    AppComponent,MenuBarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    SportClubUserManagementModule,
    SportClubEventsModule,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
