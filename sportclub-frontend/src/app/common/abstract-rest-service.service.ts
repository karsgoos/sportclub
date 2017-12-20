import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Config} from './config';

export abstract class AbstractRestService<T> {
  constructor(protected http: HttpClient, private endPoint: string) {
  }

  mockTest(): Observable<T> {
    return this.http.get<T>(Config.API_URL + this.endPoint);
  }

  // Add here all the CRUD operations ( create, read, update, delete )

  save(toSave:T):Observable<T>{
    return this.http.post(Config.API_URL + this.endPoint,toSave);
  }

  update(toUpdate: T): Observable<T> {
    return this.http.put(Config.API_URL + this.endPoint, toUpdate);
  }

}
