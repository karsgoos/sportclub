import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';



const routes: Routes = [
  {
    path: 'user-management',
    component: SportClubUserManagementComponent
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
