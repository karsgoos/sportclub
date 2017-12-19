import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment} from '../../../environments/environment';
import { Observable } from "rxjs/observable";
import { Participant } from "../../common/model/participant-model";
import {Message} from "../../common/model/message-model";


@Injectable()
export class EventListParticipantsService {

  constructor(private http: HttpClient){

  }

  getParticipantsByEventId(id: number): Observable<Participant[]> {
     var url = environment.eventApiUrl + "/" + id + "/participants";

     return this.http.get<Participant[]>(url);
  }

  exportList(id: number) {
    var url = environment.eventApiUrl + "/" + id + "/deelnemers";

    window.open(url, "_blank");
  }


  getCancellationsByEventId(id: number): Observable<Message<Participant[]>> {
    var url = environment.eventApiUrl + "/" + id + "/cancellations";

    return this.http.get<Message<Participant[]>>(url);
  }

  exportCancellationsList(id: number) {
    var url = environment.eventApiUrl + "/" + id + "/cancellations/annulaties";

    window.open(url, "_blank");
  }
}
