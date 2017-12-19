import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../service/sportclub-event.service";
import {AttendingModalComponent} from "../attending-modal/attending-modal.component";
import {isNullOrUndefined} from "util";

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

  calculateAvailableSpace() {
    var currentTotal = 0;
    var countAttendancies = 0;

    if (!isNullOrUndefined(this.eventModel.attendancies)) {

      //Only calculate the attendacies that are not cancelled
      for (var i = 0; i < this.eventModel.attendancies.length; i++) {

        if (!this.eventModel.attendancies[i].cancelled) {
          countAttendancies += 1;
        }
      }
    }

    currentTotal = this.eventModel.maxParticipants - countAttendancies;

    if (currentTotal < 0) {
      return 0;
    }
    return currentTotal;
  }

  subscribe() {
    this.router.navigate(['/event/' + this.eventModel.id + '/subscribe']);

  }

  subscribeModal() {
    this.modal.show();
  }


}
