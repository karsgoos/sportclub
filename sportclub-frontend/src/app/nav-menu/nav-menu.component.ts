import {Component, OnInit, Injectable} from '@angular/core';
import {AuthenticationService} from "../login/services/authentication.service";
@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrls: ['./nav-menu.component.css']
})

@Injectable()
export class NavMenuComponent implements OnInit {

  constructor(private auth : AuthenticationService) { }

  isLoggedIn(){
    return this.auth.isAuthenticated();
  }

  logout(){
    this.auth.logout();
  }

  ngOnInit() {
  }

}
