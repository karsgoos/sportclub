import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SportClubEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Address} from "../../model/address";
import {Weekday} from "../../model/sportclub-recuring-event-info"
import {Moderator} from "../../model/moderator";
declare var $: any;

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit, AfterViewInit {

  // hack for making date and time pickers work
  ngAfterViewInit(): void {
    let self = this;
    document.getElementById('eventEndTime').onchange = function (event: any) {
      self.eventForm.patchValue({"endtime": event.target.value});
    };
    document.getElementById('eventStartTime').onchange = function (event: any) {
      self.eventForm.patchValue({"starttime": event.target.value});
    };
    document.getElementById('eventStartDate').onchange = function (event: any) {
      self.eventForm.patchValue({"startday": self.convertDateString(event.target.value)});
    };
    document.getElementById('eventEndDate').onchange = function (event: any) {
      self.eventForm.patchValue({"endday": self.convertDateString(event.target.value)});
    };

    document.getElementById('eventDeadlineTime').onchange = function (event: any) {
      self.eventForm.patchValue({"deadlinetime": event.target.value});
    };
    document.getElementById('eventDeadlineDate').onchange = function (event: any) {
      self.eventForm.patchValue({"deadlineday": self.convertDateString(event.target.value)});
    };

    document.getElementById('eventFirstDate').onchange = function (event: any) {
      self.eventForm.patchValue({"firstEventDate": self.convertDateString(event.target.value)});
    };

    document.getElementById('eventLastDate').onchange = function (event: any) {
      self.eventForm.patchValue({"lastEventDate": self.convertDateString(event.target.value)});
    };

    document.getElementById('selectModeratorBox').onchange = function (event: any) {
      self.responsible_id = event.target.value;
    };

    $('select').material_select();
  }

  isFormSubmitted: boolean = false;
  event: SportClubEvent = {};
  addr: Address = {};
  responsibles:Moderator[]=[];
  responsible_id: number;
  eventForm: FormGroup;

  constructor(private eventService: SportClubEventService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.eventForm = this.fb.group({
      name:['',Validators.required],
      description:'',
      street: ['', Validators.required],
      homeNumber:['',Validators.required],
      postalCode:['',Validators.required],
      country:['',Validators.required],
      pricePerChild:['',Validators.required],
      pricePerAdult:['',Validators.required],
      priceGeneral:['',Validators.required],
      startday:['',Validators.required],
      starttime:['',Validators.required],
      endday:['',Validators.required],
      endtime:['',Validators.required],
      deadlineday:['',Validators.required],
      deadlinetime:['',Validators.required],
      minParticipants:10, //['',Validators.required]
      maxParticipants:100, //['',Validators.required]
      closed:false,
      standardAddressBoolean:false,
      differentPricesBoolean:false,
      eventIsRecurring: false,
      firstEventDate: '',
      lastEventDate: '',
      nrOfWeekdays : this.fb.group({
        MONDAY : false,
        TUESDAY : false,
        WEDNESDAY : false,
        THURSDAY : false,
        FRIDAY : false,
        SATURDAY : false,
        SUNDAY : false
      }),
      extraModeratorsBoolean:false
      /*nrOfWeekdays : [
        {name: "Monday",value : Weekday.MONDAY, checked: false},
        {name: "Tuesday",value : Weekday.TUESDAY, checked: false},
        {name: "Wednesday",value : Weekday.WEDNESDAY, checked: false},
        {name: "Thursday",value : Weekday.THURSDAY, checked: false},
        {name: "Friday",value : Weekday.FRIDAY, checked: false},
        {name: "Saturday",value : Weekday.SATURDAY, checked: false},
        {name: "Sunday",value : Weekday.SUNDAY, checked: false},
      ]*/
    });
  }

  prepareEventToSave(){
    // if a custom address is wanted, set the address like specified in the form
    if(!this.eventForm.value.standardAddressBoolean){
      this.addr.street = this.eventForm.value.street;
      this.addr.homeNumber = this.eventForm.value.homeNumber;
      this.addr.country = this.eventForm.value.country;
      this.addr.postalCode = this.eventForm.value.postalCode;
    }
    // TODO: Make this the standard sportclub address
    else{
      this.addr.street = "SportClubStreet";
      this.addr.homeNumber = 101;
      this.addr.country = "SportClubCountry";
      this.addr.postalCode = "1000";
    }

    if(this.eventForm.value.differentPricesBoolean){
      this.event.priceChild = this.eventForm.value.pricePerChild;
      this.event.priceAdult = this.eventForm.value.pricePerAdult;
    }
    else {
      this.event.priceChild = this.eventForm.value.priceGeneral;
      this.event.priceAdult = this.eventForm.value.priceGeneral;
    }

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

    this.event.responsibles = this.responsibles;

    if (this.eventForm.value.eventIsRecurring) {
      let weekdays = [];
      var nrOfDays = this.eventForm.value.nrOfWeekdays;
      Object.entries(nrOfDays).forEach(([key, value]) =>{
        console.log(key +"--"+value);
        if(value){
          weekdays.push(key);
        }
      });
      this.event.recurringEventInfo = {
        startDate: this.eventForm.value.firstEventDate+" 00:00",
        endDate: this.eventForm.value.lastEventDate+ " 00:00",
        weekdays: weekdays as [string]
      }
    }

  }

  saveSingleEvent() {
    this.prepareEventToSave();
    this.eventService.saveEvent(this.event);
    this.isFormSubmitted = true;
  }

  isFieldValid(field: string){
    return this.eventForm.get(field).valid;
  }

  isFieldValidAndSubmitted(field: string){
    return !this.isFieldValid(field) && this.isFormSubmitted;
  }

  errorCss(field: string){
    return {
      'invalid' : this.isFieldValidAndSubmitted(field)
    }
  }

  errorRedText(field: string){
    return {'color: red' : this.isFieldValidAndSubmitted(field)}
  }

  getAllModerators():Moderator[]{
    // TODO: make this call method from some userservice
    // for now this simply generates some mock data to show something in the form.
    let mockedModerators=[{"firstName":"John", "lastName":"Doe", "id":1},{"firstName":"Robb", "lastName":"Stark", "id":2}];
    return mockedModerators;
  }

  getModeratorById(id:number):Moderator{
    // TODO: make this call method from some userservice
    // for now this simply generates some mock data to show something in the form.
    let moderators = this.getAllModerators();
    for(let moderator of moderators){
      if(moderator.id == id){
        return moderator;
      }
    }
  }

  addResponsibleModerator(){
    this.responsibles.push(this.getModeratorById(this.responsible_id));
  }

  clearResponsibleModerators(){
    this.responsibles = [];
  }



  convertDateString(dateString: string): string {
    let temp = dateString.split(" ");
    let day: string = temp[0];
    let year: string = temp[2];
    let month: string;
    switch (temp[1]) {
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
        month = "00";
    }

    return year + '/' + month + '/' + day;
  }
}
