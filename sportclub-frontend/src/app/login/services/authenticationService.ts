import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {forEach} from '@angular/router/src/utils/collection';

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/x-www-form-urlencoded');

    const data = 'grant_type=password&username=' + username + '&password=' + password;

    return this.http.post<any>('http://localhost:8080/oauth/token', data, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded'),
      withCredentials: true
    }).map(user => {
      localStorage.setItem('access_token', user.access_token);
      localStorage.setItem('refresh_token', user.refresh_token);

      var authorities: string[] = [];

      for (const authority of user.authorities) {
        authorities.push(authority['authority']);
      }

      localStorage.setItem('authorities', authorities.join(','));
    });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }

  getCurrentUsername(): string {
    // TODO get actual current user!!!
    return "user@user.user";
  }
}
