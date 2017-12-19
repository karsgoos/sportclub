import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../service/sportclub-event.service";
import {AttendingModalComponent} from "../attending-modal/attending-modal.component";
import { EventListParticipantsComponent } from "../event-list-participants/event-list-participants.component";

declare var $ :any;

@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  eventModel: SportClubEvent;

  @ViewChild(AttendingModalComponent) modal: AttendingModalComponent;
  @ViewChild(EventListParticipantsComponent) participants: EventListParticipantsComponent;

  constructor(private route: ActivatedRoute, private router:Router, private sportClubEventService :SportClubEventService) {  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.sportClubEventService.getEvent(params['id']).subscribe(eventModel => this.eventModel = eventModel);
    });
    $('.materialboxed').materialbox();
  }



  subscribe(){
    this.router.navigate(['/event/'+this.eventModel.id+'/subscribe']);

  }

  subscribeModal(){
    this.modal.show();
  }

  showParticipantsModal() {
    this.participants.show();
  }


}
