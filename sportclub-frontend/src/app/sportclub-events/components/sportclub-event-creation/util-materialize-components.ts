
import {FormGroup} from "@angular/forms";
import {convertDateString} from "./util-dateconverter";
import {SportclubEventCreationComponent} from "./sportclub-event-creation.component";

declare var $: any;

export function initDateTimeComponents(form: FormGroup){
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

