import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {ApiComponent} from './api/api.component';
import {LoginComponent} from './login/login.component';
import {PointsComponent} from './points/points.component';

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
  },
  {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'points',
    component: PointsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
