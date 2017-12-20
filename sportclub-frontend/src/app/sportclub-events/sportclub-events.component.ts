import {Component, OnInit, ViewChild} from '@angular/core';
import {SportClubEventService} from './service/sportclub-event.service';
import {SportClubEvent} from '../common/model/sportclub-event-model';
import {EventOverviewModalComponent} from "./event-overview-modal/event-overview-modal.component";
import {Router} from "@angular/router";
import {AuthenticationService} from "../login/services/authentication.service";

@Component({
  selector: 'app-sportclub-events',
  templateUrl: './sportclub-events.component.html',
  styleUrls: ['./sportclub-events.component.css']
})
export class SportClubEventsComponent implements OnInit {

  events: SportClubEvent[];

  @ViewChild(EventOverviewModalComponent) overviewModal: EventOverviewModalComponent;

  constructor(private router: Router, private eventService: SportClubEventService, private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.eventService.getEvents().subscribe(events => this.events = events);
  }

  eventOverviewModal() {
    this.overviewModal.show();
  }

  goToEventCreation() {
    this.router.navigate(['/evenementen/aanmaken'])
  }

  isModerator(): boolean {
    return this.authService.getCurrentAuthorities().includes('MODERATOR_PRIVILEGES');
  }

}
