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
    path: 'attendingModal',
    component: AttendingModalComponent

  },
  {
    path: 'events',
    component: SportClubEventsComponent
  },
  {
    path: 'eventDetail/:id',
    component : EventDetailComponent
  },
  {
    path: 'eventSubscribe/:id',
    component: AttendingModalComponent

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
