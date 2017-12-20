import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventOverviewModalComponent } from './event-overview-modal.component';

describe('EventOverviewModalComponent', () => {
  let component: EventOverviewModalComponent;
  let fixture: ComponentFixture<EventOverviewModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventOverviewModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventOverviewModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
