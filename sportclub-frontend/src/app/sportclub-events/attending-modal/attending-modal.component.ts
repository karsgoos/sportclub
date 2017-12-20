import { Component, OnInit } from '@angular/core';
import {SportClubEvent} from "../../common/model/sportclub-event-model";
import {SportClubEventService} from "../service/sportclub-event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup,Validators} from "@angular/forms";
declare var $ :any;

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
  validForm:boolean;


  constructor(private sportServ:SportClubEventService,private router:Router,private route:ActivatedRoute,private fb:FormBuilder) {
    this.paramId= +this.route.snapshot.paramMap.get('id');
    this.validForm=false;
    this.sportServ.getEvent(this.paramId).subscribe(eventModel => {
      this.eventModel = eventModel;
    });
    this.rForm = fb.group({
      'naam': ['' ,[Validators.required,Validators.pattern(/[a-zA-Z\s]+$/)]],
      'voornaam': ['' ,[Validators.required,Validators.pattern(/[a-zA-Z\s]+$/)]],
      'email':[null,[Validators.required,Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)]],
      'numberOfAdults':[null, Validators.compose([Validators.required, Validators.min(0)])],
      'numberOfChildren':[null,Validators.compose([Validators.required, Validators.min(0)])]
    })


  }

  ngOnInit() {

    this.paramId= +this.route.snapshot.paramMap.get('id');

    this.sportServ.getEvent(this.paramId).subscribe(eventModel => {
      this.eventModel=eventModel
    });
  }

  addEvent(post) {
    this.validForm=true;
    this.paramId = +this.route.snapshot.paramMap.get('id');

    this.sportServ.getEvent(this.paramId).subscribe(eventModel => {
      this.eventModel = eventModel;
      this.naam = post.naam;
      this.voornaam = post.voornaam;
      this.email=post.email;
      this.numberOfAdults = post.numberOfAdults;
      this.numberOfChildren = post.numberOfChildren;

      this.sportServ.subscribeEvent(this.eventModel.id, this.naam, this.voornaam, this.email, this.numberOfAdults, this.numberOfChildren);

      this.router.navigate(['/events']);

    });


  }

  show(){
    $('#modal1').modal();
  }



}
