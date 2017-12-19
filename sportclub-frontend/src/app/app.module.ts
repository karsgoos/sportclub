import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {HttpModule} from '@angular/http';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginModule} from './login/login.module';
import {FormsModule} from '@angular/forms';
import {PointsComponent} from './points/points.component';
import {AuthInterceptor} from './login/services/auth.interceptor';
import {AuthenticationService} from './login/services';
import {NavMenuComponent} from './nav-menu/nav-menu.component';
import {RegistrationComponent} from './registration/registration.component';
import {RegisteredUserGuard} from './guard/registered-user.guard';

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
    RegisteredUserGuard,
    AuthenticationService],
  imports: [
    BrowserModule,
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
