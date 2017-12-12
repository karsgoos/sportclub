import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../sportclub-events/model/sportclub-event";

@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  event: SportClubEvent;

  constructor() { }

  ngOnInit() {
  }

}
