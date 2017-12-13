import { Component, OnInit } from '@angular/core';
import { SportClubEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";
import {FormGroup, FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit {

  eventForm: FormGroup;
  date: Date = new Date();


  constructor(private eventService: SportClubEventService, private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm(){
    this.eventForm = this.fb.group({
      name:'',
      description:'',
      address:this.fb.group({
        street:'',
      }),
      price:this.fb.group({
        child:0,
        adult:0,
        elderly:0,
      })
    });
  }




  saveSingleEvent(){
    console.log(this.eventForm.value);
    this.eventService.saveEvent(this.eventForm.value);
  }

}
