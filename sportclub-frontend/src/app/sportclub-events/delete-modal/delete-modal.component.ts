import {Component, Injector, OnInit} from '@angular/core';
import {EventDetailComponent} from "../event-detail/event-detail.component";
import {SportClubEventService} from "../service/sportclub-event.service";
import {Router} from "@angular/router";

declare var $: any;

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent implements OnInit {

  id: number;

  constructor(private router: Router, private eventService: SportClubEventService) { }

  ngOnInit() {
  }

  show() {
    $('#deleteModal').modal();
  }

  confirmDelete() {
    this.eventService.deleteEvent(this.id).subscribe(_ => {
      this.router.navigate(['/events']);
    });
  }

  setId(param: number) {
    this.id = param;
  }
}
