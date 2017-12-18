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
import {RoleManagementPanelComponent} from './role-management-panel/role-management-panel.component';

import {AttendingModalComponent} from "./sportclub-events/attending-modal/attending-modal.component";

@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    EventDetailComponent,
    AttendingModalComponent,
    RoleManagementPanelComponent
  ],
  imports: [
    BrowserModule,
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
