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
    });
  }

  saveSingleEvent(){
    console.log(this.eventForm.value);
    this.eventService.saveEvent(this.eventForm.value);
  }

}
