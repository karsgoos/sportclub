import { Component, OnInit } from '@angular/core';
import { Participant } from '../../common/model/participant-model';
import { EventListParticipantsService } from "../service/event-list-participants.service";
import { ActivatedRoute } from "@angular/router";
import {AuthenticationService} from "../../login/services/authentication.service";

declare var $ :any;

@Component({
  selector: 'app-event-list-participants',
  templateUrl: './event-list-participants.component.html',
  styleUrls: ['./event-list-participants.component.css']
})
export class EventListParticipantsComponent implements OnInit {
  participants: Participant[];
  paramId: number;

  constructor(private eventListParticipantsService : EventListParticipantsService, private route: ActivatedRoute, private authService: AuthenticationService) {
    this.paramId = +this.route.snapshot.paramMap.get('id');
    if(this.isModerator()) {
      this.eventListParticipantsService.getParticipantsByEventId(this.paramId)
        .subscribe(participants => {
          this.participants = participants;
        });
    }
   }

  ngOnInit() {

  }

  show() {
    $('#participantsModal').modal();

  }

  exportList() {
    this.eventListParticipantsService.exportList(this.paramId);
  }

  isModerator(): boolean {
    return this.authService.getCurrentAuthorities().includes('MODERATOR_PRIVILEGES');
  }


}
