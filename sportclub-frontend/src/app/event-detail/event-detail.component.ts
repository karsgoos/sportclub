import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../common/model/sportclub-event-model";


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
