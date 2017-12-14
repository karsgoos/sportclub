import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {EventDetailComponent} from "./event-detail/event-detail.component";
import {AttendingModalComponent} from "./attending-modal/attending-modal.component";


const routes: Routes = [
  {
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
    path: 'event/:id',pathMatch: 'full',
    component : EventDetailComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
