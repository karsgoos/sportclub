import {Injectable} from '@angular/core';
import {AbstractRestService} from '../../common/abstract-rest-service.service';
import {SportClubEvent} from '../model/sportclub-event';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class SportClubEventService extends AbstractRestService<SportClubEvent> {

  constructor(http: HttpClient) {
    // replace 5185415ba171ea3a00704eed with endpoint of the appropriate rest controller.
    super(http, 'localhost:4200/api');
  }

  saveEvent(event:SportClubEvent){
    console.log("in service" + event)
    super.save(event);
  }

}
