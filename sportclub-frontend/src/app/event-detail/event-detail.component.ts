import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../common/model/sportclub-event-model";
import {Router} from "@angular/router";


@Component({
  selector: 'event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  event: SportClubEvent;

  constructor(private router:Router) { }

  ngOnInit() {
  }
  navigate(){
    this.router.navigate(['/attendingModal'+event]);

  }


}
