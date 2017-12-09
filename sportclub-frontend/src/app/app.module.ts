import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SportClubUserManagementModule,
    SportClubEventsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
