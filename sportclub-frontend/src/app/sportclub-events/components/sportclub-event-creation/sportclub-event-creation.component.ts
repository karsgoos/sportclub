import { Component, OnInit } from '@angular/core';
import { SportClubEvent} from "../../model/sportclub-event";

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit {

  event: SportClubEvent;

  constructor() { }

  ngOnInit() {
  }

}
