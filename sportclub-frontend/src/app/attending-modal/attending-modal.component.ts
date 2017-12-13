import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../common/model/sportclub-event-model";
import {SportClubEventService} from "../sportclub-events/service/sportclub-event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup,Validators} from "@angular/forms";

@Component({
  selector: 'app-attending-modal',
  templateUrl: './attending-modal.component.html',
  styleUrls: ['./attending-modal.component.css']
})
export class AttendingModalComponent implements OnInit {
  eventModel: SportClubEvent;
  paramId: number;
  rForm:FormGroup;
  naam:string;
  voornaam:string;
  email:string;
  numberOfAdults:number;
  numberOfChildren:number;


  constructor(private sportServ:SportClubEventService,private router:Router,private route:ActivatedRoute,private fb:FormBuilder) {
    this.rForm = fb.group({
      'naam': [null,Validators.required],
      'voornaam': [null,Validators.required]
    })


  }

  ngOnInit() {
    this.paramId= +this.route.snapshot.paramMap.get('id');
    this.sportServ.getEvent(this.paramId).subscribe(eventModel => this.eventModel=eventModel);
  }

}
