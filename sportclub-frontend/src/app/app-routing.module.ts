import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportClubUserManagementComponent} from './sportclub-user-management/sportclub-user-management.component';
import {SportClubEventsComponent} from './sportclub-events/sportclub-events.component';
import {RoleManagementPanelComponent} from './role-management-panel/role-management-panel.component';
import {LoginComponent} from './login/login.component';
import {PointsComponent} from './points/points.component';
import {RegistrationComponent} from "./registration/registration.component";
import {RegisteredUserGuard} from './guard/registered-user.guard';
import {SportclubEventCreationComponent} from "./sportclub-events/components/sportclub-event-creation/sportclub-event-creation.component";
import {EventDetailComponent} from "./sportclub-events/event-detail/event-detail.component";
import {AttendingModalComponent} from "./sportclub-events/attending-modal/attending-modal.component";
import {ModeratorGuard} from "./guard/moderator.guard";

const routes: Routes = [
  {
    path: 'evenementen/aanmaken',
    component: SportclubEventCreationComponent,
    canActivate: [ModeratorGuard]
  },
  {
    path: 'evenementen/aanpassen/:id', pathMatch: 'full',
    component: SportclubEventCreationComponent,
    canActivate: [ModeratorGuard]
  },
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
    path: 'role-management',
    component: RoleManagementPanelComponent
  },
  {
    path: 'event/:id',pathMatch: 'full',
    component : EventDetailComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'points',
    component: PointsComponent,
    canActivate: [RegisteredUserGuard]
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'role-management',
    component: RoleManagementPanelComponent
  },
  {
    path: 'event/:id',pathMatch: 'full',
    component : EventDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
