import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventListParticipantsService} from "../service/event-list-participants.service";
import {Participant} from "../../common/model/participant-model";

declare var $ :any;

@Component({
  selector: 'app-cancellations-modal',
  templateUrl: './cancellations-modal.component.html',
  styleUrls: ['./cancellations-modal.component.css']
})
export class CancellationsModalComponent implements OnInit {

  paramId: number;
  cancellations: Participant[];

  constructor(private route: ActivatedRoute, private eventListParticipantsService: EventListParticipantsService) { }

  ngOnInit() {
    this.paramId = +this.route.snapshot.paramMap.get('id');
    console.log("The id that I am looking for = " + this.paramId);
    //this.participants = this.eventListParticipantsService.getParticipantsByEventId(this.paramId);
    this.eventListParticipantsService.getCancellationsByEventId(this.paramId)
      .subscribe(cancellations => {
        this.cancellations = cancellations.value;
      });
  }

  show() {
    $('#cancellationsModal').modal();
  }

  exportList() {
    this.eventListParticipantsService.exportCancellationsList(this.paramId);
  }

}
