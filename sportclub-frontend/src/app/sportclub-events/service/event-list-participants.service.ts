import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { environment} from '../../../environments/environment';
import { Participant } from "../../common/model/participant-model";
import {Message} from "../../common/model/message-model";
import {AuthenticationService} from "../../login/services/authentication.service";
import {Observable} from "rxjs/Observable";


@Injectable()
export class EventListParticipantsService {

  constructor(private http: HttpClient, private authService: AuthenticationService){

  }

  getParticipantsByEventId(id: number): Observable<Participant[]> {
     var url = environment.eventApiUrl + "/" + id + "/participants";

     return this.http.get<Participant[]>(url);
  }

  exportList(id: number) {
    var url = environment.eventApiUrl + "/" + id + "/deelnemers";

    this.http.get(url, {
      headers: new HttpHeaders().set("Accept", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
      responseType: 'blob'
    }).subscribe(data => {
      this.downloadExcelFile()(data, "deelnemers.xlsx");
    });
  }

  // This is a workaround. We need to open the file, but we want it to have a decent filename.
  // Sadly this is not possible, so we need to create a hidden <a> tag and add the file to it
  // as a download...
  private downloadExcelFile() {
    var a = document.createElement("a");
    document.body.appendChild(a);
    a.setAttribute("style", "display: none");
    return function (data, fileName) {
      let blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
      let url = window.URL.createObjectURL(blob);
      a.href = url;
      a.download = fileName;
      a.click();
      window.URL.revokeObjectURL(url);
    };
  }

  getCancellationsByEventId(id: number): Observable<Message<Participant[]>> {
    var url = environment.eventApiUrl + "/" + id + "/cancellations";

    return this.http.get<Message<Participant[]>>(url);
  }

  exportCancellationsList(id: number) {
    var url = environment.eventApiUrl + "/" + id + "/cancellations/annulaties";

    this.http.get(url, {
      headers: new HttpHeaders().set("Accept", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
      responseType: 'blob'
    }).subscribe(data => {
      this.downloadExcelFile()(data, "annulaties.xlsx");
    });
  }
}
