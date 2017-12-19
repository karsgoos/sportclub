import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';

import {User} from '../../common/user';
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient, private router: Router) {
  }

  // NICO: Attempt login
  login(username: string, password: string) {
    const data = 'grant_type=password&username=' + username + '&password=' + password;

    //centrale pad instellen (basepath + api url)
    return this.http.post<any>('http://localhost:8080/oauth/token', data, {
      headers: new HttpHeaders(),
      withCredentials: true
    }).map(authorization => {
      localStorage.setItem('access_token', authorization.access_token);
      localStorage.setItem('refresh_token', authorization.refresh_token);

      var authorities: string[] = [];

      for (const authority of authorization.authorities) {
        authorities.push(authority['authority']);
      }

      localStorage.setItem('authorities', authorities.join(','));
    });
  }

    /*USER api method voorzien waarbij je de usergegevens zonder pwd krijgt*/
  fetchUser() {
    return this.http.get('http://localhost:8080/api/user')
      .map(user => localStorage.setItem('user', JSON.stringify(user)), () => localStorage.removeItem('access_token'));
  }

  // NICO: Try refreshing access token
  refresh() {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/x-www-form-urlencoded');

    localStorage.removeItem('access_token');

    const data = 'grant_type=refresh_token&refresh_token=' + localStorage.getItem('refresh_token');

    return this.http.post<any>('http://localhost:8080/oauth/token', data, {
      headers: headers,
      withCredentials: true
    }).do(authorization => {
      localStorage.setItem('access_token', authorization.access_token);
    });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('authorities');
    localStorage.removeItem('user');

    this.router.navigate(['/login']);
  }

  isAuthenticated(){
    return !!localStorage.getItem('user');
  }

  getCurrentUser() {
    const jsonUser = localStorage.getItem('user');

    if (jsonUser) {
      const currentUser: User = JSON.parse(jsonUser);

      return currentUser;
    } else {
      return null;
    }
  }

}
