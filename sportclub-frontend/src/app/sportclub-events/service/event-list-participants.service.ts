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

  //fake data as long as the service doesn't work
  //getParticipantsByEventId(id: number): Participant[] {

  //  return this.participants;
  }

  private participants: Participant[] = [
  {
    firstName: "Marcske",
    lastName: "Vertongen",
    numberOfAdults: 5,
    numberOfChildren: 2
  },
  {
    firstName: "Balthazar",
    lastName: "Boma",
    numberOfAdults: 3,
    numberOfChildren: 0
  }
];


}
