import {Injectable} from '@angular/core';
import {AbstractRestService} from '../../common/abstract-rest-service.service';
import {SportClubEvent} from '../../common/model/sportclub-event-model';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/Observable";
import {SportClubCreationEvent} from "../model/sportclub-event";
import {Message} from "../../common/model/message-model";

@Injectable()
export class SportClubEventService extends AbstractRestService<SportClubCreationEvent> {

  constructor(http: HttpClient) {
    super(http, 'events');
  }

  // replace 5185415ba171ea3a00704eed with endpoint of the appropriate rest controller.
  //super(http, '5a2bfe2e2f00007112039335');


  getEvents(): Observable<SportClubEvent[]> {
    return this.http.get<SportClubEvent[]>(environment.eventApiUrl);
  }

  getEventsTimeline(page, pageSize): Observable<Message<string>> {
    return this.http.get<SportClubEvent[]>(environment.eventApiUrl + '/timeline',
      {params: new HttpParams().set('page', page).set('pageSize', pageSize)});
  }

  getEvent(id: number): Observable<SportClubEvent> {
    var url = environment.eventApiUrl + "/" + id;

    return this.http.get<SportClubEvent>(url);
  }

  getCreationEvent(id: number): Observable<SportClubCreationEvent> {
    var url = environment.eventApiUrl + "/" + id;

    return this.http.get<SportClubCreationEvent>(url);
  }

  lookupEvent(name: string): Observable<SportClubEvent> {
    var url = environment.eventApiUrl + "/search?name=" + name;

    return this.http.get<SportClubEvent>(url);
  }

  /*  @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "api/events/attend")
	public void attendEvent(@RequestParam("userId") String userId,@RequestParam("eventId") String eventId, @RequestParam("nrOfAdults") int nrOfAdults, @RequestParam("nrOfChildren")  int nrOfChildren){
		eventService.attendEvent(userId, eventId, nrOfAdults, nrOfChildren);
	}*/


  saveEvent(event: SportClubCreationEvent): Observable<Message<string>> {
    return super.save(event);
  }

  subscribeEvent(eventId, naam, voornam, email, nbAdults, nbChild) {
    return this.http
      .post(environment.eventApiUrl + '/attend', {
        eventId: eventId,
        lastName: naam,
        firstName: voornam,
        email: email,
        nrOfAdults: nbAdults,
        nrOfChildren: nbChild
      })
      .subscribe();

    // return this.http
    //   .post(environment.eventApiUrl+'/attend',{userId:1,eventId:eventId,nrOfAdults:nbAdults,nrOfChildren:nbChild})
    //   .subscribe();

  }

  updateEvent(event: Message<string>) {
    super.update(event).subscribe();
  }

  saveAttachment(file: File, eventId: number){

      let params = new HttpParams();
      let formData = new FormData();
      formData.append('file', file);
      const options = {
        headers: new HttpHeaders(),
        params: params,
      }

      return this.http.post('http://localhost:8080/api/events/'+eventId+'/attachment', formData, options);
  }

  saveImage(image: File, eventId: number){

    let params = new HttpParams();
    let formData = new FormData();
    formData.append('file', image);
    const options = {
      headers: new HttpHeaders(),
      params: params,
    }

    return this.http.post('http://localhost:8080/api/events/'+eventId+'/image', formData, options);

  }

}
