import {Component, OnInit} from '@angular/core';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEvent} from '../common/model/sportclub-event-model';
//import {SportClubEvent} from '';

@Component({
  selector: 'app-sportclub-events',
  templateUrl: './sportclub-events.component.html',
  styleUrls: ['./sportclub-events.component.css']
})
export class SportClubEventsComponent implements OnInit {

  sportClubEvent: SportClubEvent;

  constructor(public sportClubEventService: SportClubEventService) {
  }

  ngOnInit() {
    // this.sportClubEventService.mockTest()
    //   .subscribe((data) => {
    //       this.sportClubEvent = data;
    //     }
    //   );
  }

}
