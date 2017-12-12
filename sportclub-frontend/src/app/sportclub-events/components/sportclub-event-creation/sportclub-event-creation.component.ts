import { Component, OnInit } from '@angular/core';
import { SportClubEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit {

  event: SportClubEvent;

  constructor(private eventService: SportClubEventService) { }

  ngOnInit() {
  }

  saveSingleEvent(){
    this.eventService.saveEvent(this.event);
  }

}
