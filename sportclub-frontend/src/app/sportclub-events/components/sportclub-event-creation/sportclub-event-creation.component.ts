import {AfterViewChecked, AfterViewInit, Component, OnInit} from '@angular/core';
import {SportClubCreationEvent} from "../../model/sportclub-event";
import {SportClubEventService} from "../../service/sportclub-event.service";
import {FormGroup, FormBuilder} from "@angular/forms";
import {Moderator} from "../../model/moderator";
import {EnrollmentTemp} from "../../model/enrollment-temp";
import {ActivatedRoute, Router} from "@angular/router";
import {
  initDateTimeComponents, initDropDownMenus, initFileUploader,
  initImageUploader
} from "./util-materialize-components";
import {backendDateToDay, backendDateToTime, dateToPickerString, timeToPickerString} from "./util-dateconverter";
import {checkGlobalValidation, markElementsAsDirty, setLocalValidators} from "./util-validation";
import {fromFormToEvent} from "./util-event-form-conversion";
import {DateAdapter} from "@angular/material/core"
import {AuthenticationService} from "../../../login/services/authentication.service";
import {User} from "../../../common/user";

declare var $: any;

@Component({
  selector: 'app-sportclub-event-creation',
  templateUrl: './sportclub-event-creation.component.html',
  styleUrls: ['./sportclub-event-creation.component.css']
})
export class SportclubEventCreationComponent implements OnInit,  AfterViewInit{

  // variables
  private recurringEventId: number;
  event: SportClubCreationEvent = {};
  eventForm: FormGroup;

  responsibles:Moderator[]=[];
  responsible_id: number;

  enrollments:EnrollmentTemp[]=[];
  enrollment_id: number;

  eventId: number;

  globalErrorMessages: string[] = [];

  attachedFile: File;
  fileIsAttached:boolean = false;
  attachedImage: File;
  imageIsAttached:boolean = false;

  todayVariable = new Date();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private eventService: SportClubEventService,
              private fb: FormBuilder,
              private adapter: DateAdapter<any>,
              private authService: AuthenticationService) {
  }

  ngAfterViewInit(): void {
    // manually adding change event listeners to adapt the FormGroup for date and time pickers
    // this isn't necessary anymore thnks to new datepickers
    // initDateTimeComponents(this.eventForm);
    // do some essential hacking for dropdown menus
    //initDropDownMenus(this);
    // add change event for the file uploader
    initFileUploader(this);
    // add change event for the image uploader
    initImageUploader(this);
  }

  ngOnInit() {
    this.adapter.setLocale('nl');
    this.route.paramMap.subscribe(params => {
      if (params.get('id')) { // Edit page
        this.eventId = Number(params.get('id'));
        this.loadForm();
      } else { // Create page
        this.createForm();
      }
    });
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
        for (let i = 0; i < event.recurringEventInfo.weekdays.length; i++) {
          let weekday = event.recurringEventInfo.weekdays[i];
          nrOfWeekdays[weekday] = true;
        }
        this.eventForm.controls['firstEventDate'].setValue(backendDateToDay(event.recurringEventInfo.startDate));
        this.eventForm.controls['lastEventDate'].setValue(backendDateToDay(event.recurringEventInfo.endDate));
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
        customMinMaxParticipantsBoolean: (!(event.minParticipants == 1 && event.maxParticipants == 999999)),
        customDeadlineBoolean: !(event.deadline == event.startDate),
        nrOfWeekdays: nrOfWeekdays,
        closed: event.closed,
        extraModeratorsBoolean: event.responsibles.length > 1,
        points: event.points,
        startday: backendDateToDay(event.startDate),
        starttime: backendDateToTime(event.startDate),
        endday: backendDateToDay(event.endDate),
        endtime: backendDateToTime(event.endDate),
        customAddressBoolean:false,
        differentPricesBoolean: (!(event.priceAdult==event.priceChild)),
        automaticReminderMailBoolean: !(event.reminderDate==undefined),
        automaticModeratorMailBoolean: !(event.numberParticipantsToSendMail==0)
      });

      if (this.eventForm.value.customMinMaxParticipantsBoolean){
        this.eventForm.patchValue({
          minParticipants: event.minParticipants,
          maxParticipants: event.maxParticipants
        });
      }
      if (this.eventForm.value.customDeadlineBoolean) {
        this.eventForm.patchValue({
          deadlineday: backendDateToDay(event.deadline),
          deadlinetime: backendDateToTime(event.deadline)
        });
      }
      if (this.eventForm.value.automaticReminderMailBoolean) {
        this.eventForm.patchValue({
          reminderMailDate: backendDateToDay(event.reminderDate),
          reminderMailTime: backendDateToTime(event.reminderDate)
        });
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

      name:'',
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
      startday:'',
      starttime:'',
      endday:'',
      endtime:'',
      customDeadlineBoolean: false,
      deadlineday:'',
      deadlinetime:'',

      customAddressBoolean:false,
      street:'',
      homeNumber:'',
      postalCode:'',
      country:'',
      city:'',

      points:'0',

      differentPricesBoolean:false,
      pricePerChild:'0',
      pricePerAdult:'0',
      priceGeneral:'0',

      closed:false,

      extraModeratorsBoolean:false,

      automaticReminderMailBoolean:false,
      reminderMailDate:'',
      reminderMailTime:'',
      automaticModeratorMailBoolean:false,

    });

    //Add the local validators
    setLocalValidators(this.eventForm);
  }

  saveEvent() {
    this.globalErrorMessages = checkGlobalValidation(this.eventForm);
    if(this.globalErrorMessages.length>0){
      // mark the required fields as dirty to show where the errors are
      markElementsAsDirty(this.eventForm);

    }
    else {
      //save the event
      // first create the object to be send to the backend
      this.event = fromFormToEvent(this.eventForm, this.responsibles, this.enrollments, this.recurringEventId, this.authService);
      // if we are updating
      if (this.eventId) {
        this.event.id = this.eventId;
        this.eventService.updateEvent(this.event).subscribe(message => {
          let event = message.value;
          this.uploadAttachments(event);
        });
      }
      // if we are creating
      else {
        this.eventService.saveEvent(this.event).subscribe(message => {
          if (message.error) {
            console.error(message.error);
            return;
          }
          let event = message.value;
          this.uploadAttachments(event);
        });
      }
    }
  }

  private uploadAttachments(event: SportClubCreationEvent) {
    let id = event.id;
    if (this.fileIsAttached) {
      this.eventService.saveAttachment(this.attachedFile, id).subscribe(
        success => {
          this.uploadImage(id);
        },
        error => {
          if (error.error.error) {
            this.globalErrorMessages.push(error.error.error);
          }
        });
    } else {
      this.uploadImage(id);
    }
  }

  private uploadImage(id: any) {
    if (this.imageIsAttached) {
      this.eventService.saveImage(this.attachedImage, id).subscribe(
        success => {
          if (this.globalErrorMessages.length === 0) {
            this.router.navigate(['/event', id]);
          }
        },
        error => {
          if (error.error) {
            this.globalErrorMessages.push(error.error);
          }
        });
    } else {
      if (this.globalErrorMessages.length === 0) {
        this.router.navigate(['/event', id]);
      }
    }
  }

  /*
  return all of the moderators to show them in a dropdown menu
   */
  //getAllModerators():User[]{
    // TODO: make this call method from some userservice
    // for now this simply generates some mock data to show something in the form.
/*
    let user:User = {"_firstName":"John", "_lastName":"Doe", "_id":1, "_email":"joh_doe@hotmail.com", "_totalPoints":"0", "_role":"moderator", "_enrollments":[]}

    let mockedModerators=[{"_firstName":"John", "_lastName":"Doe", "_id":1, "_email":"joh_doe@hotmail.com", "_total_points":"0", "_role":"moderator", "_enrollments":[]},
              {"_firstName":"Robb", "_lastName":"Stark", "_id":2, "_email":"joh_doe@hotmail.com", "_total_points":"0", "_role":"moderator", "_enrollments":[]}];



    return mockedModerators;*/
  //}

  /*
  get one of the moderators by its id
   *//*
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
*/
  /*
  add the moderator that is currently clicked to a list of responsibles
   */
  /*
  addResponsibleModerator(){
    this.responsibles.push(this.getModeratorById(this.responsible_id));
  }
*/
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

}
