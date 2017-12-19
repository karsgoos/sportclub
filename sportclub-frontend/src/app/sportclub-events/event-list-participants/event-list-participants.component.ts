import { Component, OnInit } from '@angular/core';
import { Participant } from '../../common/model/participant-model';
import { EventListParticipantsService } from "../service/event-list-participants.service";
import { ActivatedRoute } from "@angular/router";

declare var $ :any;

@Component({
  selector: 'app-event-list-participants',
  templateUrl: './event-list-participants.component.html',
  styleUrls: ['./event-list-participants.component.css']
})
export class EventListParticipantsComponent implements OnInit {
  participants: Participant[];
  paramId: number;

  constructor(private eventListParticipantsService : EventListParticipantsService, private route: ActivatedRoute) {
    this.paramId = +this.route.snapshot.paramMap.get('id');
    console.log("The id that I am looking for = " + this.paramId);
    //this.participants = this.eventListParticipantsService.getParticipantsByEventId(this.paramId);
     this.eventListParticipantsService.getParticipantsByEventId(this.paramId)
       .subscribe(participants => {
         this.participants = participants;
       });
   }

  ngOnInit() {

  }

  show() {
    $('#participantsModal').modal();

  }
}
