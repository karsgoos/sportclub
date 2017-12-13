import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, RequestMethod, RequestOptionsArgs} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ApiService {

  constructor(private _http: Http) {}

  doPost(url): Observable<any> {
    const username = 'angular';
    const password = 'secret';
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));

    const requestArg: RequestOptionsArgs = { headers: headers, method: 'POST', withCredentials: true };

    const data = 'grant_type=password&username=bert&password=kek';

    return this._http.post(url, data, requestArg);
  }

  doGet(url): Observable<any> {

    const username = 'angular';
    const password = 'secret';
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));

    const requestArg: RequestOptionsArgs = { headers: headers, method: 'GET', withCredentials: true };

    return this._http.get(url, requestArg);
  }
}
