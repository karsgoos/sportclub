import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {
  }

  // NICO: Attempt login
  login(username: string, password: string) {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/x-www-form-urlencoded');

    const data = 'grant_type=password&username=' + username + '&password=' + password;

    return this.http.post<any>('http://localhost:8080/oauth/token', data, {
      headers: headers,
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

  // NICO: Try refreshing access token
  refresh() {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/x-www-form-urlencoded');

    localStorage.removeItem('access_token');

    const data = 'grant_type=refresh_token&token=' + localStorage.getItem('refresh_token');

    return this.http.post<any>('http://localhost:8080/oauth/token', data, {
      headers: headers,
      withCredentials: true
    }).map(data => {
      localStorage.setItem('access_token', data.access_token);
    });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('authorities');
    localStorage.removeItem('user');
  }
}
