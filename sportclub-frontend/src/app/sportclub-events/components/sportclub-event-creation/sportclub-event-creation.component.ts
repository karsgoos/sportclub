import {AfterViewInit, Component, OnInit} from '@angular/core';
import { SportClubEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";
import {FormGroup, FormBuilder} from "@angular/forms";
import {Address} from "../../model/address";

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit, AfterViewInit {

  // hack for making date and time pickers work
  ngAfterViewInit(): void {
    let self = this;
    document.getElementById('eventEndTime').onchange= function(event: any) {
      self.eventForm.patchValue({"endtime": event.target.value});
    };
    document.getElementById('eventStartTime').onchange= function(event: any) {
      self.eventForm.patchValue({"starttime": event.target.value});
    };
    document.getElementById('eventStartDate').onchange= function(event: any) {
      self.eventForm.patchValue({"startday": self.convertDateString(event.target.value)});
    };
    document.getElementById('eventEndDate').onchange= function(event: any) {
      self.eventForm.patchValue({"endday": self.convertDateString(event.target.value)});
    };

    document.getElementById('eventDeadlineTime').onchange= function(event: any) {
      self.eventForm.patchValue({"deadlinetime": event.target.value});
    };
    document.getElementById('eventDeadlineDate').onchange= function(event: any) {
      self.eventForm.patchValue({"deadlineday": self.convertDateString(event.target.value)});
    };
  }

  event: SportClubEvent = {};
  addr: Address = {};
  eventForm: FormGroup;

  constructor(private eventService: SportClubEventService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm(){
    this.eventForm = this.fb.group({
      name:'',
      description:'',
      street:'',
      homeNumber:'',
      postalCode:'',
      country:'',
      pricePerChild:'',
      pricePerAdult:'',
      startday:'',
      starttime:'',
      endday:'',
      endtime:'',
      deadlineday:'',
      deadlinetime:'',
      minParticipants:10,
      maxParticipants:100,
      closed:false
    });
  }

  prepareEventToSave(){
    this.addr.street = this.eventForm.value.street;
    this.addr.homeNumber = this.eventForm.value.homeNumber;
    this.addr.country = this.eventForm.value.country;
    this.addr.postalCode = this.eventForm.value.postalCode;

    this.event.priceChild = this.eventForm.value.pricePerChild;
    this.event.priceAdult = this.eventForm.value.pricePerAdult;

    this.event.name = this.eventForm.value.name;
    this.event.description = this.eventForm.value.description;
    this.event.address = this.addr;
    this.event.startDate = this.eventForm.value.startday + " " + this.eventForm.value.starttime;
    this.event.endDate = this.eventForm.value.endday + " " + this.eventForm.value.endtime;
    this.event.deadline = this.eventForm.value.deadlineday + " " + this.eventForm.value.deadlinetime;
    this.event.responsibles = [];
    this.event.enrollments = [];
    this.event.minParticipants = this.eventForm.value.minParticipants;
    this.event.maxParticipants = this.eventForm.value.maxParticipants;
    this.event.closed = this.eventForm.value.closed;

  }

  saveSingleEvent(){
    this.prepareEventToSave();
    this.eventService.saveEvent(this.event);
  }





  convertDateString(dateString:string):string{
    let temp = dateString.split(" ");
    let day:string = temp[0];
    let year:string = temp[2];
    let month:string;
    switch(temp[1]) {
      case "January,":
        month = "01";
        break;
      case "February,":
        month = "02";
        break;
      case "March,":
        month = "03";
        break;
      case "April,":
        month = "04";
        break;
      case "May,":
        month = "05";
        break;
      case "June,":
        month = "06";
        break;
      case "July,":
        month = "07";
        break;
      case "August,":
        month = "08";
        break;
      case "September,":
        month = "09";
        break;
      case "October,":
        month = "10";
        break;
      case "November,":
        month = "11";
        break;
      case "December,":
        month = "12";
        break;
      default:
        month="00";
    }

    return year + '/' + month + '/' + day;
  }
}
