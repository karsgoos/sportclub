import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {ActivatedRoute, Router} from "@angular/router";
import {SportClubEventService} from "../service/sportclub-event.service";
import {AttendingModalComponent} from "../attending-modal/attending-modal.component";
import {environment} from "../../../environments/environment";
import {DeleteModalComponent} from "../delete-modal/delete-modal.component";

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

  constructor(private route: ActivatedRoute, private router: Router, private sportClubEventService: SportClubEventService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.sportClubEventService.getEvent(params['id']).subscribe(eventModel => this.eventModel = eventModel);
      this.deleteModal.setId(params['id']);
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
    this.router.navigate(['/evenementen/aanpassen', this.eventModel.id]);
  }

  deleteEvent() {
    this.deleteModal.show();
  }

  getImageUrl() {
    return environment.eventApiUrl + "/" + this.eventModel.id + "/image";
  }


}
