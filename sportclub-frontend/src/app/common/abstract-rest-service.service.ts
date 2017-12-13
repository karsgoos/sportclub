import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {Config} from './config';

export abstract class AbstractRestService<T> {
  constructor(private http: HttpClient, private endPoint: string) {
  }

  mockTest(): Observable<T> {
    return this.http.get<T>(Config.SERVER_URL + this.endPoint);
  }

  event

  // Add here all the CRUD operations ( create, read, update, delete )

  save(toSave:T):Observable<T>{
    console.log("in the final saving method with following url");
    console.log(Config.SERVER_URL + this.endPoint);
    console.log(toSave);
    let testObject = {
      "name": "testEvent",
      "description":"Description of the event",
      "priceAdult": 100,
      "priceChild": 10,
      "endDate":"2017/12/25 13:00",
      "startDate":"2017/12/25 11:00",
      "deadline":"2017/12/23 13:00",
      "address":{
        "homeNumber":"20",
        "postalCode":"3020",
        "street":"keizersstraat",
        "country": "Belgium"
      },
      "responsibles":[],
      "enrollments":[],
      "minParticipants":"20",
      "maxParticipants":"100",
      "closed":"true"
    };
    console.log(testObject);
    return this.http.post(Config.SERVER_URL + this.endPoint,testObject);
  }

}
