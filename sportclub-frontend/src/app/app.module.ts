import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {SportClubUserManagementModule} from './sportclub-user-management/sportclub-user-management.module';
import {SportClubEventsModule} from './sportclub-events/sportclub-events.module';
import {RoleManagementPanelComponent} from './role-management-panel/role-management-panel.component';
import {MenuBarComponent} from './menu-bar/menu-bar.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginModule} from './login/login.module';
import {FormsModule} from '@angular/forms';
import {PointsComponent} from './points/points.component';
import {AuthInterceptor} from './login/services/auth.interceptor';
import {AuthenticationService} from './login/services';
import {RegistrationComponent} from './registration/registration.component';
import {RegisteredUserGuard} from './guard/registered-user.guard';

@NgModule({
  declarations: [
    AppComponent,
    RoleManagementPanelComponent,
    MenuBarComponent,
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
    AuthenticationService],
  imports: [
    BrowserModule,
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
