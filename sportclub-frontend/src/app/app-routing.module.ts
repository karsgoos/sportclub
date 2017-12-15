import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {EventDetailComponent} from "./event-detail/event-detail.component";
import {AttendingModalComponent} from "./attending-modal/attending-modal.component";
import {RoleManagementPanelComponent} from './role-management-panel/role-management-panel.component';

import {AppComponent} from "./app.component";
import {SportclubEventCreationComponent} from "./sportclub-events/components/sportclub-event-creation/sportclub-event-creation.component";


const routes: Routes = [
  {
    path: 'events/creation',
    component: SportclubEventCreationComponent
  },{
    path: 'user-management',  pathMatch: 'full',
    component: SportClubUserManagementComponent
  },
  {
    path: 'event/:id/subscribe',pathMatch: 'full',
    component: AttendingModalComponent
  },
  {
    path: 'events',pathMatch: 'full',
    component: SportClubEventsComponent
  },
  {
    path: 'role-management',
    component: RoleManagementPanelComponent
  },
  {
    path: 'event/:id',pathMatch: 'full',
    component : EventDetailComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
