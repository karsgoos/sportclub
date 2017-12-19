import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment} from '../../../environments/environment';
import { Observable } from "rxjs/observable";
import { Participant } from "../../common/model/participant-model";


@Injectable()
export class EventListParticipantsService {

  constructor(private http: HttpClient){

  }

  getParticipantsByEventId(id: number): Observable<Participant[]> {
     var url = environment.eventApiUrl + "/" + id + "/participants";

     return this.http.get<Participant[]>(url);
  }

  exportList(id: number) {
    var url = environment.eventApiUrl + "/" + id + "/attendees";

    window.open(url, "_blank");
  }


}
