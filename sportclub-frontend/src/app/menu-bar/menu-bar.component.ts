import {Component} from '@angular/core';
import {AuthenticationService} from '../login/services/authentication.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent {
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
