import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {AuthenticationService} from '../login/services';

@Injectable()
export class RegisteredUserGuard implements CanActivate {
  constructor(private authService: AuthenticationService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!!this.authService.getCurrentAuthorities()) {
      return this.authService.getCurrentAuthorities().includes('REGISTERED_USER_PRIVILEGES');
    }

    return !!this.authService.getCurrentAuthorities();
  }
}
