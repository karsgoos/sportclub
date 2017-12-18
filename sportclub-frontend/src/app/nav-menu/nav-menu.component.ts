import {Component, OnInit, Injectable} from '@angular/core';
import {AuthenticationService} from '../login/services/authentication.service';

@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrls: ['./nav-menu.component.css']
})

@Injectable()
export class NavMenuComponent {
  constructor(private auth: AuthenticationService) {
  }

  getUser() {
    return this.auth.getCurrentUser();
  }

  isLoggedIn() {
    return this.auth.isAuthenticated();
  }

  logout() {
    this.auth.logout();
  }
}
