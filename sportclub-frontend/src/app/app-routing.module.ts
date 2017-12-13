import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {EventDetailComponent} from "./event-detail/event-detail.component";
import {AttendingModalComponent} from "./attending-modal/attending-modal.component";

const routes: Routes = [
  {
    path: 'user-management',
    component: SportClubUserManagementComponent
  },
  {
    path: 'eventDetail',
    component : EventDetailComponent

  },
  {
    path: 'attendingModal',
    component: AttendingModalComponent

  },
  {
    path: 'events',
    component: SportClubEventsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
