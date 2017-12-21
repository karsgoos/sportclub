import {SportClubCreationEvent} from "../../model/sportclub-event";
import {FormGroup} from "@angular/forms";
import {Address} from "../../model/address";
import {Moderator} from "../../model/moderator";
import {EnrollmentTemp} from "../../model/enrollment-temp";
import {convertDateStringNew} from "./util-dateconverter";

/*
 A function to create an event that can be send to the api, by the values that were retrieved in the form
  */
export function fromFormToEvent(form:FormGroup, responsibles:Moderator[], enrollments: EnrollmentTemp[], recurringEventId:number):SportClubCreationEvent{

  let event:SportClubCreationEvent = {};
  let addr: Address = {};

  event.name = form.value.name;
  event.description = form.value.description;

  if(form.value.customMinMaxParticipantsBoolean){
    event.minParticipants = form.value.minParticipants;
    event.maxParticipants = form.value.maxParticipants;
  }
  else{
    event.minParticipants = 1;
    event.maxParticipants = 999999;
  }

  if(form.value.eventIsRecurring){
    // these values actually don't really matter anymore when facing recurring events, so we just take dates of the period
    event.startDate = convertDateStringNew(form.value.firstEventDate.toLocaleString()) + " " + form.value.starttime;
    event.endDate = convertDateStringNew(form.value.lastEventDate.toLocaleString()) + " " + form.value.endtime;
  }
  else{
    event.startDate = convertDateStringNew(form.value.startday.toLocaleString()) + " " + form.value.starttime;
    event.endDate = convertDateStringNew(form.value.endday.toLocaleString()) + " " + form.value.endtime;
  }
  // if we want a custom deadline take the values of the form, else set some default
  if(form.value.customDeadlineBoolean){
    event.deadline = convertDateStringNew(form.value.deadlineday.toLocaleString()) + " " + form.value.deadlinetime;
  }
  // is this a default that makes sense??
  else {
      event.deadline = event.startDate;

  }
  event.points = form.value.points;
  // create the recurrent event info if necessary, else it can just be null
  if (form.value.eventIsRecurring) {
    let weekdays = [];
    var nrOfDays = form.value.nrOfWeekdays;
    Object.entries(nrOfDays).forEach(([key, value]) =>{
      if(value){
        weekdays.push(key);
      }
    });
    event.recurringEventInfo = {
      // this one important if we are updating a recurring event
      id: recurringEventId,
      startDate: convertDateStringNew(form.value.firstEventDate.toLocaleString())+" 00:00",
      endDate: convertDateStringNew(form.value.lastEventDate.toLocaleString())+ " 00:00",
      weekdays: weekdays as [string]
    }
  }

  // if a custom address is wanted, set the address like specified in the form
  if(form.value.customAddressBoolean){
    addr.street = form.value.street;
    addr.homeNumber = form.value.homeNumber;
    addr.country = form.value.country;
    addr.postalCode = form.value.postalCode;
    addr.city = form.value.city;
  }
  // TODO: Make this the standard sportclub address gotten out of some service
  else{
    addr.street = "SportClubStreet";
    addr.homeNumber = "101";
    addr.country = "SportClubCountry";
    addr.postalCode = "1000";
    addr.city = "SportClubCity";
  }
  event.address = addr;

  if(form.value.differentPricesBoolean){
    event.priceChild = form.value.pricePerChild;
    event.priceAdult = form.value.pricePerAdult;
  }
  else {
    event.priceChild = form.value.priceGeneral;
    event.priceAdult = form.value.priceGeneral;
  }

  if(form.value.extraModeratorsBoolean){
    event.responsibles = responsibles;
  }
  else{
    event.responsibles = [];
  }

  event.closed = form.value.closed;
  if(form.value.closed) {
    event.enrollments = enrollments;
  }
  else{
    event.enrollments = [];
  }

  // if a reminder email should be send, add the info for it in the object
  if(form.value.automaticReminderMailBoolean){
    event.reminderDate = convertDateStringNew(form.value.reminderMailDate.toLocaleString()) + " " + form.value.reminderMailTime;
  }
  if(form.value.automaticModeratorMailBoolean){
    if(form.value.customMinMaxParticipantsBoolean) {
      event.numberParticipantsToSendMail = form.value.minParticipants;
    }
  }

  return event;
}
