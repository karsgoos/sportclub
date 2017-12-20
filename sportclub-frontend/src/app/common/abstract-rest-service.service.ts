import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Config} from './config';

export abstract class AbstractRestService<T> {
  constructor(private http: HttpClient, private endPoint: string) {
  }

  mockTest(): Observable<T> {
    return this.http.get<T>(Config.API_URL + this.endPoint);
  }

  // Add here all the CRUD operations ( create, read, update, delete )
}
