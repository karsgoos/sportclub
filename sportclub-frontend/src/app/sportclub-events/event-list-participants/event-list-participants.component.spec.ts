import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventListParticipantsComponent } from './event-list-participants.component';

describe('EventListParticipantsComponent', () => {
  let component: EventListParticipantsComponent;
  let fixture: ComponentFixture<EventListParticipantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventListParticipantsComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventListParticipantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
