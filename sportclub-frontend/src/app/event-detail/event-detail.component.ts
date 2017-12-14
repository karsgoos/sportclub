import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../sportclub-events/service/sportclub-event.service";
declare var $ :any;

@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  eventModel: SportClubEvent;

  constructor(private route: ActivatedRoute, private router:Router, private sportClubEventService :SportClubEventService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.sportClubEventService.getEvent(params['id']).subscribe(eventModel => this.eventModel = eventModel);
    });
  }



  subscribe(){
  this.router.navigate(['/event/'+this.eventModel.id+'/subscribe']);

  }

  subscribeModal(){
    $('#modal1').modal();
  }


}
