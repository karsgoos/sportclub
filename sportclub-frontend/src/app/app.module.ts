import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {HttpModule} from '@angular/http';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {HttpClientModule} from '@angular/common/http';
import {ApiModule} from './api/api.module';
import {LoginModule} from './login/login.module';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ApiModule,
    HttpModule,
    LoginModule,
    HttpClientModule,
    AppRoutingModule,
    SportClubUserManagementModule,
    SportClubEventsModule,
    FormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
