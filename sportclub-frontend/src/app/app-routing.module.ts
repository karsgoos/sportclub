import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {ApiComponent} from './api/api.component';

const routes: Routes = [
  {
    path: 'user-management',
    component: SportClubUserManagementComponent
  },
  {
    path: 'events',
    component: SportClubEventsComponent
  },
  {
    path: 'apitest',
    component: ApiComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
