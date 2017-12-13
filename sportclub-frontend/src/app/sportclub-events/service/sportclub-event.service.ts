import {Injectable} from '@angular/core';
import {AbstractRestService} from '../../common/abstract-rest-service.service';
import {SportClubEvent} from '../model/sportclub-event';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class SportClubEventService extends AbstractRestService<SportClubEvent> {

  constructor(http: HttpClient) {
    super(http, 'events');
  }

  saveEvent(event:SportClubEvent){
    super.save(event).subscribe();
  }

}
