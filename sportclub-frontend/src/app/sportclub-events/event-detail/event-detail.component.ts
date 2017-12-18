import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../service/sportclub-event.service";
import {AttendingModalComponent} from "../attending-modal/attending-modal.component";

declare var $: any;

@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  eventModel: SportClubEvent;

  @ViewChild(AttendingModalComponent) modal: AttendingModalComponent;

  constructor(private route: ActivatedRoute, private router: Router, private sportClubEventService: SportClubEventService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.sportClubEventService.getEvent(params['id']).subscribe(eventModel => this.eventModel = eventModel);
    });
    $('.materialboxed').materialbox();
  }


  subscribe() {
    this.router.navigate(['/event/' + this.eventModel.id + '/subscribe']);

  }

  subscribeModal() {
    this.modal.show();
  }

  editEvent() {
    this.router.navigate(['/events/edit', this.eventModel.id]);
  }


}
