import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../service/sportclub-event.service";
import {AttendingModalComponent} from "../attending-modal/attending-modal.component";
import {environment} from "../../../environments/environment";
import {DeleteModalComponent} from "../delete-modal/delete-modal.component";
import {EventListParticipantsComponent} from "../event-list-participants/event-list-participants.component";

import {isNullOrUndefined} from "util";
import {CancellationsModalComponent} from "../cancellations-modal/cancellations-modal.component";
import {AuthenticationService} from "../../login/services/authentication.service";

declare var $: any;

@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  eventModel: SportClubEvent;

  @ViewChild(AttendingModalComponent) modal: AttendingModalComponent;
  @ViewChild(DeleteModalComponent) deleteModal: DeleteModalComponent;
  @ViewChild(EventListParticipantsComponent) participants: EventListParticipantsComponent;
  @ViewChild(CancellationsModalComponent) cancellations: CancellationsModalComponent;

  constructor(private route: ActivatedRoute, private router: Router, private sportClubEventService: SportClubEventService, private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.sportClubEventService.getEvent(params['id']).subscribe(eventModel => this.eventModel = eventModel);
      this.deleteModal.setId(params['id']);
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

  editEvent() {
    this.router.navigate(['/evenementen/aanpassen', this.eventModel.id]);
  }

  deleteEvent() {
    this.deleteModal.show();
  }

  getImageUrl() {
    return environment.eventApiUrl + "/" + this.eventModel.id + "/image";
  }

  showParticipantsModal() {
    this.participants.show();
  }

  showCancellationsModal() {
    this.cancellations.show();
  }

  isModerator(): boolean {
    return this.authService.getCurrentAuthorities().includes('MODERATOR_PRIVILEGES');
  }

  isAdministrator(): boolean {
    return this.authService.getCurrentAuthorities().includes('ADMINISTRATOR_PRIVILEGES');
  }

}
