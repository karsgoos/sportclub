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
      'voornaam': [null,Validators.required],
      'email':[null,Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)],
      'numberOfAdults':[null,Validators.required],
      'numberOfChildren':[null,Validators.required]
    })


  }

  ngOnInit() {
    this.paramId= +this.route.snapshot.paramMap.get('id');

    this.sportServ.getEvent(this.paramId).subscribe(eventModel => {
      this.eventModel=eventModel
      console.log(this.eventModel.name);
    });
  }

  addEvent(post){

    this.naam=post.naam;
    this.voornaam=post.voornaam;
    this.numberOfAdults=post.numberOfAdults;
    this.numberOfChildren=post.numberOfChildren;
    this.sportServ.subscribeEvent(1,this.paramId,this.numberOfAdults,this.numberOfChildren);
  }

}
