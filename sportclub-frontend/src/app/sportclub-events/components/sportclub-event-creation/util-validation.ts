

import {FormGroup, Validators} from "@angular/forms";
import {composeDate} from "./util-dateconverter";

export function setLocalValidators(form: FormGroup){
  // set init validators
  form.get('name').setValidators([Validators.required, Validators.minLength(2)]);
  form.get('startday').setValidators([Validators.required]);
  form.get('starttime').setValidators([Validators.required, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')]);
  form.get('endday').setValidators([Validators.required]);
  form.get('endtime').setValidators([Validators.required, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')]);
  form.get('points').setValidators([Validators.min(0)]);
  form.get('priceGeneral').setValidators([Validators.required]);

  // set conditional validators
  addConditionalValidatorsShortcut(form,'minParticipants',
    'customMinMaxParticipantsBoolean',
    [Validators.required,Validators.pattern('[1-9][0-9]*')],
    true);
  addConditionalValidatorsShortcut(form,'maxParticipants',
    'customMinMaxParticipantsBoolean',
    [Validators.required,Validators.pattern('[1-9][0-9]*')],
    true);
  addConditionalValidatorsShortcut(form,'numberParticipantsToRemind',
    'automaticModeratorMailBoolean',
    [Validators.required,Validators.pattern('[1-9][0-9]*')],
    true);

  addConditionalValidatorsShortcut(form,'startday',
    'eventIsRecurring',
    [Validators.required],
    false);
  addConditionalValidatorsShortcut(form,'endday',
    'eventIsRecurring',
    [Validators.required],
    false);
  addConditionalValidatorsShortcut(form,'deadlineday',
    'customDeadlineBoolean',
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'deadlinetime',
    'customDeadlineBoolean',
    [Validators.required, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')],
    true);
  addConditionalValidatorsShortcut(form,'firstEventDate',
    'eventIsRecurring',
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'lastEventDate',
    'eventIsRecurring',
    [Validators.required],
    true);

  addConditionalValidatorsShortcut(form,'street',
    'customAddressBoolean',
    [Validators.required, Validators.pattern('[^0-9]+')],
    true);
  addConditionalValidatorsShortcut(form,'postalCode',
    'customAddressBoolean',
    [Validators.required, Validators.pattern('[0-9]+')],
    true);
  addConditionalValidatorsShortcut(form,'city',
    'customAddressBoolean',
    [Validators.required, Validators.pattern('[^0-9]+')],
    true);
  addConditionalValidatorsShortcut(form,'homeNumber',
    'customAddressBoolean',
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'country',
    'customAddressBoolean',
    [Validators.required, Validators.pattern('[^0-9]+')],
    true);

  addConditionalValidatorsShortcut(form,'priceGeneral',
    'differentPricesBoolean',
    [Validators.required],
    false);
  addConditionalValidatorsShortcut(form,'pricePerChild',
    'differentPricesBoolean',
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'pricePerAdult',
    'differentPricesBoolean',
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'reminderMailDate',
    "automaticReminderMailBoolean",
    [Validators.required],
    true);
  addConditionalValidatorsShortcut(form,'reminderMailTime',
    "automaticReminderMailBoolean",
    [Validators.required, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')],
    true);
}

function addConditionalValidatorsShortcut(form, validatingElementName, conditionElementName, validators, obligedWhenChecked){
  addConditionalValidators(form.get(validatingElementName),
    form.get(conditionElementName),
    validators,
    obligedWhenChecked);
}

function addConditionalValidators(validatingElement, conditionElement, validators, obligedWhenChecked){
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
export function checkGlobalValidation(form:FormGroup): string[]{

  // clear previous messages
  let globalErrorMessages = [];

  //check if the asynchronous simple validation is okay
  if(!form.valid){
    globalErrorMessages.push("Niet alle velden in het formulier zijn correct ingevuld");
    //TODO maybe mark them as dirty then such that it is more clear which fields?
  }

  // check if the max number participants isn't lower then the min number
  if(form.value.maxParticipants<form.value.minParticipants){
    globalErrorMessages.push('Het minimum aantal deelnemers moet lager liggen dan het maximum aantal deelnemers');
  }
  // check if start date of the event isn't after the end date if single event
  let startDate = composeDate(form.value.startday, form.value.starttime);
  let endDate = composeDate(form.value.endday, form.value.endtime);
  if(!form.value.eventIsRecurring) {
    if (endDate < startDate) {
      globalErrorMessages.push("Een evenement kan niet eindigen voor het gestart is");
    }
  }
  // check dates of the period if recurring event
  else{
    let startPeriod = new Date(form.value.firstEventDate);
    let endPeriod = new Date(form.value.lastEventDate);
    let startTime = new Date("01/01/2000 " + form.value.starttime);
    let endTime = new Date("01/01/2000 " + form.value.endtime);
    // check if end date is not before start date
    if(startPeriod > endPeriod){
      globalErrorMessages.push("De periode kan niet eindigen voor hij gestart is");
    }
    // check if start time is not after end time
    else if(startTime > endTime){
      globalErrorMessages.push("Het einduur kan niet voor het beginuur liggen");
    }
    else{
      let tempDate = new Date();
      tempDate.setDate(startPeriod.getDate()+7);
      // check if period is longer then a week
      if(tempDate > endPeriod){
        globalErrorMessages.push("De periode moet minstens een week in beslag nemen");
      }
      // check if at least one of the weekdays is selected
      else if(!weekdaysSelected(form)){
        globalErrorMessages.push("Er werden geen weekdagen geselecteerd voor het evenement");
      }
    }
  }

  // if there is a deadline, check if it is before the startdate of the event
  let deadlineDate = composeDate(form.value.deadlineday, form.value.deadlinetime);
  if(deadlineDate > startDate){
    globalErrorMessages.push("De deadline moet voor de start van het evenement liggen");
  }

  // if mailtime is set, check if it is before the start
  if(form.value.automaticReminderMailBoolean){
    let mailDate = new Date(form.value.reminderMailDate + " " + form.value.reminderMailTime);
    if(mailDate > startDate){
      globalErrorMessages.push("Je kan geen mail verzenden nadat het evenement is gestart");
    }
  }

  // if a number of participants to mail is set, check wether it is between the min and max
  if(form.value.automaticModeratorMailBoolean){
    let remindNr = form.value.numberParticipantsToRemind;
    let min = form.value.minParticipants;
    let max = form.value.maxParticipants;
    if(form.value.customMinMaxParticipantsBoolean) {
      if (remindNr < min || remindNr > max) {
        globalErrorMessages.push("Het aantal deelnemers ingesteld voor een herinnering moet tussen het minimum en het maximum aantal deelnemers liggen");
      }
    }
  }

  return globalErrorMessages;

}

function weekdaysSelected(form:FormGroup){
  let weekdays = form.value.nrOfWeekdays;
  for(let day in weekdays){
    if(weekdays.hasOwnProperty(day)){
      if(weekdays[day]){
        return true;
      }
    }
  }
  return false;
}

export function markElementsAsDirty(form:FormGroup){
  for(let control in form.controls){
    if(form.controls[control].status == "INVALID"){
      form.controls[control].markAsDirty();
    }
  }
}
