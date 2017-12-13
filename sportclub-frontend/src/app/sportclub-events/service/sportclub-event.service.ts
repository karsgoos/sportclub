import {Injectable} from '@angular/core';
import {AbstractRestService} from '../../common/abstract-rest-service.service';
import {SportClubEvent} from '../../common/model/sportclub-event-model';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/Observable";

@Injectable()
export class SportClubEventService { //extends AbstractRestService<SportClubEvent> {

  constructor(private http: HttpClient) {
    // replace 5185415ba171ea3a00704eed with endpoint of the appropriate rest controller.
    //super(http, '5a2bfe2e2f00007112039335');
  }

  getEvents() :Observable<SportClubEvent[]>{
    return this.http.get<SportClubEvent[]>(environment.eventApiUrl);
  }

  getEvent(id: number):  Observable<SportClubEvent>{
    var url = environment.eventApiUrl + "/" + id;

    return this.http.get<SportClubEvent>(url);
  }

  lookupEvent(name: string) :  Observable<SportClubEvent> {
    var url = environment.eventApiUrl + "/search?name=" + name;

    return this.http.get<SportClubEvent>(url);
  }


}
