import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SportClubCreationEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";
import {FormGroup, FormBuilder, Validators, ValidatorFn, AbstractControl} from "@angular/forms";
import {Address} from "../../model/address";
import {Moderator} from "../../model/moderator";
import {EnrollmentTemp} from "../../model/enrollment-temp";
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";
declare var $: any;

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit, AfterViewInit {

  // variables
  private recurringEventId: number;
  event: SportClubCreationEvent = {};
  addr: Address = {};
  eventForm: FormGroup;

  responsibles:Moderator[]=[];
  responsible_id: number;

  enrollments:EnrollmentTemp[]=[];
  enrollment_id: number;

  eventId: number;

  globalErrorMessages: string[] = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private eventService: SportClubEventService,
              private fb: FormBuilder) {
  }

  // hack for making date and time pickers work
  ngAfterViewInit(): void {
    let self = this;
    // manually adding change event listeners to adapt the FormGroup
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
    document.getElementById('reminderMailDate').onchange = function(event:any) {
      self.eventForm.patchValue({"reminderMailDate": self.convertDateString(event.target.value)});
    };
    document.getElementById('reminderMailTime').onchange = function(event:any){
      self.eventForm.patchValue({'reminderMailTime': event.target.value});
    };

    // to make the dropdown select boxes work
    $('select').material_select();
    document.getElementById('selectModeratorBox').onchange = function (event: any) {
      self.responsible_id = event.target.value;
    };
    document.getElementById('selectEnrollmentBox').onchange = function (event: any) {
      self.enrollment_id = event.target.value;
    };
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if (params.get('id')) { // Edit page
        this.eventId = Number(params.get('id'));
        this.loadForm();
      } else { // Create page
        this.createForm();
      }
    });
  }


  static dateToPickerString(date: Date): string {
    const monthNames = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];

    let value = String(date.getDate()) + ' ' + monthNames[date.getMonth()] + ', ' + date.getFullYear();
    return value.trim();
  }

  static timeToPickerString(date: Date): string {
    let hoursString = date.getHours() < 10 ? '0' + date.getHours() : String(date.getHours());
    let minutesString = date.getMinutes() < 10 ? '0' + date.getMinutes() : String(date.getMinutes());
    let value = String(hoursString + ':' + minutesString);
    return value.trim();
  }

  loadForm() {
    // create the formgroup
    this.createForm();

    // if we are updating an event, we want the form to contain the previous values as a default.
    this.eventService.getCreationEvent(this.eventId).subscribe(event => {
      this.event.id = event.id;

      let nrOfWeekdays = {
        MONDAY: false,
        TUESDAY: false,
        WEDNESDAY: false,
        THURSDAY: false,
        FRIDAY: false,
        SATURDAY: false,
        SUNDAY: false,
      };
      if (event.recurringEventInfo) {
        this.recurringEventId = event.recurringEventInfo.id;
        this.eventForm.controls['eventIsRecurring'].setValue(true);
        for(let i = 0; i < event.recurringEventInfo.weekdays.length; i++) {
          let weekday = event.recurringEventInfo.weekdays[i];
          nrOfWeekdays[weekday] = true;
        }
      }
      this.eventForm.patchValue({
        name: event.name,
        description: event.description,
        street: event.address.street,
        homeNumber: event.address.homeNumber,
        city: event.address.city,
        postalCode: event.address.postalCode,
        country: event.address.country,
        pricePerChild: event.priceChild,
        pricePerAdult: event.priceAdult,
        priceGeneral: event.priceAdult,
        nrOfWeekdays: nrOfWeekdays,
        closed: event.closed,
        extraModeratorsBoolean: event.responsibles.length > 1,
        points: event.points,
      });
      // Dates and times need to be set manually
      // Additionally, so that angular picks up the date and time values, we need to trigger an onchange event
      $('#eventStartDate')[0].value = SportclubEventCreationComponent.dateToPickerString(new Date(event.startDate));
      $('#eventStartDate')[0].dispatchEvent(new Event('change'));
      $('#eventStartTime')[0].value = SportclubEventCreationComponent.timeToPickerString(new Date(event.startDate));
      $('#eventStartTime')[0].dispatchEvent(new Event('change'));

      $('#eventEndDate')[0].value = SportclubEventCreationComponent.dateToPickerString(new Date(event.endDate));
      $('#eventEndDate')[0].dispatchEvent(new Event('change'));
      $('#eventEndTime')[0].value = SportclubEventCreationComponent.timeToPickerString(new Date(event.endDate));
      $('#eventEndTime')[0].dispatchEvent(new Event('change'));

      $('#eventDeadlineDate')[0].value = SportclubEventCreationComponent.dateToPickerString(new Date(event.deadline));
      $('#eventDeadlineDate')[0].dispatchEvent(new Event('change'));
      $('#eventDeadlineTime')[0].value = SportclubEventCreationComponent.timeToPickerString(new Date(event.deadline));
      $('#eventDeadlineTime')[0].dispatchEvent(new Event('change'));

      if (event.recurringEventInfo) {
        $('#eventFirstDate')[0].value = SportclubEventCreationComponent.dateToPickerString(new Date(event.recurringEventInfo.startDate));
        $('#eventFirstDate')[0].dispatchEvent(new Event('change'));
        $('#eventLastDate')[0].value = SportclubEventCreationComponent.dateToPickerString(new Date(event.recurringEventInfo.endDate));
        $('#eventLastDate')[0].dispatchEvent(new Event('change'));
      }
      // Enrollments need to be set manually
      // TODO: make sure this works when actual enrollments are added
      for(let i = 0; i < this.enrollments.length; i++) {
        let enrollment = this.enrollments[i];
      }
    });
  }

  /*
  Function to initialize a formgroup that contains all of the fields that are needed for the creation (or update)
   of an event
   */
  createForm() {
    this.eventForm = this.fb.group({

      name:['',[Validators.required, Validators.minLength(2)]],
      description:'',

      customMinMaxParticipantsBoolean: false,
      minParticipants:'',
      maxParticipants:'',

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
      startday:['',Validators.required],
      starttime:['',Validators.required],
      endday:['',Validators.required],
      endtime:['',Validators.required],
      customDeadlineBoolean: false,
      deadlineday:'',
      deadlinetime:'',

      customAddressBoolean:false,
      street:'',
      homeNumber:'',
      postalCode:'',
      country:'',
      city:'',

      points:['0', Validators.min(0)],

      differentPricesBoolean:false,
      pricePerChild:'',
      pricePerAdult:'',
      priceGeneral:['',Validators.required],

      closed:false,

      extraModeratorsBoolean:false,

      automaticReminderMailBoolean:false,
      reminderMailDate:'',
      reminderMailTime:''
    });

    //add some conditional validators
    this.addConditionalValidatorsShortcut('minParticipants',
      'customMinMaxParticipantsBoolean',
      [Validators.required,Validators.pattern('[1-9][0-9]*')],
      true);
    this.addConditionalValidatorsShortcut('maxParticipants',
      'customMinMaxParticipantsBoolean',
      [Validators.required,Validators.pattern('[1-9][0-9]*')],
      true);

    this.addConditionalValidatorsShortcut('startday',
      'eventIsRecurring',
      [Validators.required],
      false);
    this.addConditionalValidatorsShortcut('endday',
      'eventIsRecurring',
      [Validators.required],
      false);
    this.addConditionalValidatorsShortcut('deadlineday',
      'customDeadlineBoolean',
      [Validators.required],
      true);
    this.addConditionalValidatorsShortcut('deadlinetime',
      'customDeadlineBoolean',
      [Validators.required],
      true);
    this.addConditionalValidatorsShortcut('firstEventDate',
      'eventIsRecurring',
      [Validators.required],
      true);
    this.addConditionalValidatorsShortcut('lastEventDate',
      'eventIsRecurring',
      [Validators.required],
      true);

    this.addConditionalValidatorsShortcut('street',
      'customAddressBoolean',
      [Validators.required, Validators.pattern('[^0-9]+')],
      true);
    this.addConditionalValidatorsShortcut('postalCode',
      'customAddressBoolean',
      [Validators.required, Validators.pattern('[0-9]+')],
      true);
    this.addConditionalValidatorsShortcut('city',
      'customAddressBoolean',
      [Validators.required, Validators.pattern('[^0-9]+')],
      true);
    this.addConditionalValidatorsShortcut('homeNumber',
      'customAddressBoolean',
      [Validators.required],
      true);
    this.addConditionalValidatorsShortcut('country',
      'customAddressBoolean',
      [Validators.required, Validators.pattern('[^0-9]+')],
      true);

    this.addConditionalValidatorsShortcut('priceGeneral',
      'differentPricesBoolean',
      [Validators.required],
      false);
    this.addConditionalValidatorsShortcut('pricePerChild',
      'differentPricesBoolean',
      [Validators.required],
      true);
    this.addConditionalValidatorsShortcut('pricePerAdult',
      'differentPricesBoolean',
      [Validators.required],
      true);

    this.addConditionalValidatorsShortcut('reminderMailDate',
      "automaticReminderMailBoolean",
      [Validators.required],
      true);
  }

  addConditionalValidatorsShortcut(validatingElementName, conditionElementName, validators, obligedWhenChecked){
    this.addConditionalValidators(this.eventForm.get(validatingElementName),
        this.eventForm.get(conditionElementName),
        validators,
        obligedWhenChecked);
  }

  addConditionalValidators(validatingElement, conditionElement, validators, obligedWhenChecked){
    conditionElement.valueChanges.subscribe(
      (newValue:boolean)=>{
        if(newValue==obligedWhenChecked){
          validatingElement.setValidators(validators);
        }
        else{
          validatingElement.setValidators([]);
        }
        validatingElement.updateValueAndValidity();
    });
  }

  /*
  A function to check the more complex validation, which are more complicated to do on the components individually
   */
  checkGlobalValidation(){

    // clear previous messages
    this.globalErrorMessages = [];

    //check if the asynchronous simple validation is okay
    if(!this.eventForm.valid){
      this.globalErrorMessages.push("Niet alle velden in het formulier zijn correct ingevuld");
      //TODO maybe mark them as dirty then such that it is more clear which fields?
    }

    // check if the max number participants isn't lower then the min number
    if(this.eventForm.value.maxParticipants<this.eventForm.value.minParticipants){
      this.globalErrorMessages.push('Het minimum aantal deelnemers moet lager liggen dan het maximum aantal deelnemers');
    }
    // check if start date of the event isn't after the end date if single event
    let startDate = new Date(this.eventForm.value.startday + " " + this.eventForm.value.starttime);
    let endDate = new Date(this.eventForm.value.endday + " " + this.eventForm.value.endtime);
    if(!this.eventForm.value.eventIsRecurring) {
      if (endDate < startDate) {
        this.globalErrorMessages.push("Een evenement kan niet eindigen voor het gestart is");
      }
    }
    // check dates of the period if recurring event
    else{
      let startPeriod = new Date(this.eventForm.value.firstEventDate);
      let endPeriod = new Date(this.eventForm.value.lastEventDate);
      let startTime = new Date("01/01/2000 " + this.eventForm.value.starttime);
      let endTime = new Date("01/01/2000 " + this.eventForm.value.endtime);
      // check if end date is not before start date
      if(startPeriod > endPeriod){
        this.globalErrorMessages.push("De periode kan niet eindigen voor hij gestart is");
      }
      // check if start time is not after end time
      else if(startTime > endTime){
        this.globalErrorMessages.push("Het einduur kan niet voor het beginuur liggen");
      }
      else{
        let tempDate = new Date();
        tempDate.setDate(startPeriod.getDate()+7);
        // check if period is longer then a week
        if(tempDate > endPeriod){
          this.globalErrorMessages.push("De periode moet minstens een week in beslag nemen");
        }
        // check if at least one of the weekdays is selected
        else if(!this.weekdaysSelected()){
          this.globalErrorMessages.push("Er werden geen weekdagen geselecteerd voor het evenement");
        }
      }
    }

    // if there is a deadline, check if it is before the startdate of the event
    let deadlineDate = new Date(this.eventForm.value.deadlineday + " " + this.eventForm.value.deadlinetime);
    if(deadlineDate > startDate){
      this.globalErrorMessages.push("De deadline moet voor de start van het evenement liggen");
    }

    // if mailtime is set, check if it is before the start
    if(this.eventForm.value.automaticReminderMailBoolean){
      let mailDate = new Date(this.eventForm.value.reminderMailDate + " " + this.eventForm.value.reminderMailTime);
      if(mailDate > startDate){
        this.globalErrorMessages.push("Je kan geen mail verzenden nadat het evenement is gestart");
      }
    }

  }

  weekdaysSelected(){
    let weekdays = this.eventForm.value.nrOfWeekdays;
    for(let day in weekdays){
      if(weekdays.hasOwnProperty(day)){
        if(weekdays[day]){
          return true;
        }
      }
    }
    return false;
  }


  /*
  A function to create an event that can be send to the api, by the values that were retrieved in the form
   */
  prepareEventToSave(){

    this.event.name = this.eventForm.value.name;
    this.event.description = this.eventForm.value.description;

    if(this.eventForm.value.customMinMaxParticipantsBoolean){
      this.event.minParticipants = this.eventForm.value.minParticipants;
      this.event.maxParticipants = this.eventForm.value.maxParticipants;
    }
    else{
      //TODO: are these default values that are okay? How to show this on detail pages?
      this.event.minParticipants = 1;
      this.event.maxParticipants = 999999;
    }

    if(this.eventForm.value.eventIsRecurring){
      // these values actually don't really matter anymore when facing recurring events, so we just take dates of the period
      this.event.startDate = this.eventForm.value.firstEventDate + " " + this.eventForm.value.starttime;
      this.event.endDate = this.eventForm.value.lastEventDate + " " + this.eventForm.value.endtime;
    }
    else{
      this.event.startDate = this.eventForm.value.startday + " " + this.eventForm.value.starttime;
      this.event.endDate = this.eventForm.value.endday + " " + this.eventForm.value.endtime;
    }
    // if we want a custom deadline take the values of the form, else set some default
    if(this.eventForm.value.customDeadlineBoolean){
      this.event.deadline = this.eventForm.value.deadlineday + " " + this.eventForm.value.deadlinetime;
    }
    // is this a default that makes sense??
    else {
      this.event.deadline = this.eventForm.value.startday + " 00:00";

    }
    this.event.points = this.eventForm.value.points;
    // create the recurrint event info if necessary, else it can just be null
    if (this.eventForm.value.eventIsRecurring) {
      let weekdays = [];
      var nrOfDays = this.eventForm.value.nrOfWeekdays;
      Object.entries(nrOfDays).forEach(([key, value]) =>{
        if(value){
          weekdays.push(key);
        }
      });
      this.event.recurringEventInfo = {
        // this one important if we are updating a recurring event
        id: this.recurringEventId,
        startDate: this.eventForm.value.firstEventDate+" 00:00",
        endDate: this.eventForm.value.lastEventDate+ " 00:00",
        weekdays: weekdays as [string]
      }
    }

    // if a custom address is wanted, set the address like specified in the form
    if(this.eventForm.value.customAddressBoolean){
      this.addr.street = this.eventForm.value.street;
      this.addr.homeNumber = this.eventForm.value.homeNumber;
      this.addr.country = this.eventForm.value.country;
      this.addr.postalCode = this.eventForm.value.postalCode;
      this.addr.city = this.eventForm.value.city;
    }
    // TODO: Make this the standard sportclub address gotten out of some service
    else{
      this.addr.street = "SportClubStreet";
      this.addr.homeNumber = "101";
      this.addr.country = "SportClubCountry";
      this.addr.postalCode = "1000";
      this.addr.city = "SportClubCity";
    }
    this.event.address = this.addr;

    if(this.eventForm.value.differentPricesBoolean){
      this.event.priceChild = this.eventForm.value.pricePerChild;
      this.event.priceAdult = this.eventForm.value.pricePerAdult;
    }
    else {
      this.event.priceChild = this.eventForm.value.priceGeneral;
      this.event.priceAdult = this.eventForm.value.priceGeneral;
    }

    if(this.eventForm.value.extraModeratorsBoolean){
      this.event.responsibles = this.responsibles;
    }
    else{
      this.event.responsibles = [];
    }

    this.event.closed = this.eventForm.value.closed;
    if(this.eventForm.value.closed) {
      this.event.enrollments = this.enrollments;
    }
    else{
      this.event.enrollments = [];
    }

    // if a reminder email should be send, add the info for it in the object
    if(this.eventForm.value.automaticReminderMailBoolean){
      this.event.reminderDate = this.eventForm.value.reminderMailDate + " " + this.eventForm.value.reminderMailTime;
    }
  }

  saveEvent() {
    this.checkGlobalValidation();
    if(this.globalErrorMessages.length>0){
      //do nothing, cause the information is not yet valid
    }
    else {
      //save the event
      // first create the object to be send to the backend
      this.prepareEventToSave();
      // if we are updating
      if (this.eventId) {
        this.eventService.updateEvent(this.event);
      }
      // if we are creating
      else {
        this.eventService.saveEvent(this.event).subscribe(event => {
          this.router.navigate(['/event', event.id]);
        });
      }
    }
  }


  /*
  return all of the moderators to show them in a dropdown menu
   */
  getAllModerators():Moderator[]{
    // TODO: make this call method from some userservice
    // for now this simply generates some mock data to show something in the form.
    let mockedModerators=[{"firstName":"John", "lastName":"Doe", "id":1},{"firstName":"Robb", "lastName":"Stark", "id":2}];
    return mockedModerators;
  }

  /*
  get one of the moderators by its id
   */
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

  /*
  add the moderator that is currently clicked to a list of responsibles
   */
  addResponsibleModerator(){
    this.responsibles.push(this.getModeratorById(this.responsible_id));
  }

  /*
  clear the temporarily saved list of moderators
   */
  clearResponsibleModerators(){
    this.responsibles = [];
  }

  // this method is currently implemented with mockdata
  //TODO: This should be a method calling some service to retrieve all the actual enrollments in the system!!
  getAllEnrollments():EnrollmentTemp[]{
    let mockEnrollments = [{"name":"18+ 2016-2017","id":1},{"name":"U17 2017-2018","id":2},{"name":"U15 2017-2018","id":3}];
    return mockEnrollments;
  }

  //TODO: This should be a method calling some kind of service. This is a temporal implementation
  getEnrollmentById(id:number):EnrollmentTemp{
    let allEnrollments = this.getAllEnrollments();
    for(let enrollment of allEnrollments){
      if(enrollment.id == id){
        return enrollment;
      }
    }
  }

  /*
  add the enrollment that is currently clicked to a temporarily list
   */
  addEnrollment(){
    this.enrollments.push(this.getEnrollmentById(this.enrollment_id));
  }

  /*
  clear the temporarily saved list of enrollments
   */
  clearEnrollments(){
    this.enrollments = [];
  }



  /*
  Ugly method to convert a string that represents a date like it is coming from the used datepickers, to a string that
  can be recognized in our backend api.
   */
  convertDateString(dateString: string): string {
    let temp = dateString.split(" ");
    let day: string = temp[0];
    if(day.length===1){
      day = "0" + day;
    }
    let year: string = temp[2];
    let month: string;
    switch (temp[1]) {
      case "Januari,":
        month = "01";
        break;
      case "Februari,":
        month = "02";
        break;
      case "Maart,":
        month = "03";
        break;
      case "April,":
        month = "04";
        break;
      case "Mei,":
        month = "05";
        break;
      case "Juni,":
        month = "06";
        break;
      case "Juli,":
        month = "07";
        break;
      case "Augustus,":
        month = "08";
        break;
      case "September,":
        month = "09";
        break;
      case "Oktober,":
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
