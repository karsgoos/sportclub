import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {LoginComponent} from './login/login.component';
import {PointsComponent} from './points/points.component';
import {RegistrationComponent} from "./registration/registration.component";
import {RegisteredUserGuard} from './guard/registered-user.guard';



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
    path: 'login',
    component: LoginComponent
  }, {
    path: 'points',
    component: PointsComponent,
    canActivate: [RegisteredUserGuard]
  },
  {
    path: 'registration',
    component: RegistrationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
