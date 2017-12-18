import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {HttpModule} from '@angular/http';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ApiModule} from './api/api.module';
import {LoginModule} from './login/login.module';
import {FormsModule} from '@angular/forms';
import { PointsComponent } from './points/points.component';
import {AuthInterceptor} from './login/services/auth.interceptor';
import {AuthenticationService} from './login/services';
import { NavMenuComponent } from './nav-menu/nav-menu.component';
import { RegistrationComponent } from './registration/registration.component';


@NgModule({
  declarations: [
    AppComponent,
    PointsComponent,
    NavMenuComponent,
    RegistrationComponent
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  AuthenticationService],
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
