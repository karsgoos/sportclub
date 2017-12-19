import { Component, OnInit } from '@angular/core';
import {SportClubEventService} from "../service/sportclub-event.service";
import {ActivatedRoute, Router, RouterModule} from "@angular/router";
import {SportClubEvent} from "../../common/model/sportclub-event-model";
declare var $ :any;

@Component({
  selector: 'app-event-overview-modal',
  templateUrl: './event-overview-modal.component.html',
  styleUrls: ['./event-overview-modal.component.css']
})
export class EventOverviewModalComponent implements OnInit {

  PAGE_SIZE: number = 10;

  currentPage: number = 0;
  events: SportClubEvent[];

  constructor(private router: Router, private route: ActivatedRoute, private sportClubEventService: SportClubEventService) { }

  ngOnInit() {
    this.getEvents();
  }

  show() {
    $('#modal1').modal();
  }

  earlierEvents() {
    this.currentPage--;
    this.getEvents();
  }

  laterEvents() {
    this.currentPage++;
    this.getEvents();
  }

  private getEvents() {
    this.sportClubEventService.getEventsTimeline(this.currentPage, this.PAGE_SIZE).subscribe(message => {
      if (message.error) {
        console.error(message.error);
        this.events = [];
      } else {
        this.events = message.value;
      }
    });
  }

  showEvent(eventId) {
    $('#modal1').modal('close');
    this.router.navigate(['/event', eventId]);
  }

  editEvent(eventId) {
    $('#modal1').modal('close');
    this.router.navigate(['/events/edit', eventId]);
  }

}
