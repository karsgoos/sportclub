import {Component, OnInit} from '@angular/core';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEvent} from '../common/model/sportclub-event-model';

@Component({
  selector: 'app-sportclub-events',
  templateUrl: './sportclub-events.component.html',
  styleUrls: ['./sportclub-events.component.css']
})
export class SportClubEventsComponent implements OnInit {

  events: SportClubEvent[];

  constructor(private eventService: SportClubEventService) {
  }

  ngOnInit() {
    this.eventService.getEvents().subscribe(events => this.events = events);
  }

}
