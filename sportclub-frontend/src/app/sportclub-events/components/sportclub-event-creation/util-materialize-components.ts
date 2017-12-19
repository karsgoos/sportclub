
import {FormGroup} from "@angular/forms";
import {convertDateString} from "./util-dateconverter";
import {SportclubEventCreationComponent} from "./sportclub-event-creation.component";

declare var $: any;

export function initDateTimeComponents(form: FormGroup){
  document.getElementById('eventEndTime').onchange = function (event: any) {
    form.patchValue({"endtime": event.target.value});
  };
  document.getElementById('eventStartTime').onchange = function (event: any) {
    form.patchValue({"starttime": event.target.value});
  };
  document.getElementById('eventStartDate').onchange = function (event: any) {
    form.patchValue({"startday": convertDateString(event.target.value)});
  };
  document.getElementById('eventEndDate').onchange = function (event: any) {
    form.patchValue({"endday": convertDateString(event.target.value)});
  };
  document.getElementById('eventDeadlineTime').onchange = function (event: any) {
    form.patchValue({"deadlinetime": event.target.value});
  };
  document.getElementById('eventDeadlineDate').onchange = function (event: any) {
    form.patchValue({"deadlineday": convertDateString(event.target.value)});
  };
  document.getElementById('eventFirstDate').onchange = function (event: any) {
    form.patchValue({"firstEventDate": convertDateString(event.target.value)});
  };
  document.getElementById('eventLastDate').onchange = function (event: any) {
    form.patchValue({"lastEventDate": convertDateString(event.target.value)});
  };
  document.getElementById('reminderMailDate').onchange = function(event:any) {
    form.patchValue({"reminderMailDate": convertDateString(event.target.value)});
  };
  document.getElementById('reminderMailTime').onchange = function(event:any){
    form.patchValue({'reminderMailTime': event.target.value});
  };
}

export function initDropDownMenus(comp: SportclubEventCreationComponent){
  $('select').material_select();
  document.getElementById('selectModeratorBox').onchange = function (event: any) {
    comp.responsible_id = event.target.value;
  };
  document.getElementById('selectEnrollmentBox').onchange = function (event: any) {
    comp.enrollment_id = event.target.value;
  };
}

export function initFileUploader(comp:SportclubEventCreationComponent){
  document.getElementById('attachmentFile').onchange = function(event:any){
    let fileList: FileList = event.target.files;
    if(fileList.length > 0){
      comp.fileIsAttached = true;
      comp.attachedFile = fileList[0];
    }
    else{
      comp.fileIsAttached = false;
    }
  };
}

export function initImageUploader(comp:SportclubEventCreationComponent){
  document.getElementById('eventImage').onchange = function(event:any){
    let fileList: FileList = event.target.files;
    if(fileList.length > 0){
      comp.imageIsAttached = true;
      comp.attachedImage = fileList[0];
    }
    else{
      comp.imageIsAttached = false;
    }
  };
}

