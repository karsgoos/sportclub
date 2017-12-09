import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportClubEventsComponent } from './sportclub-events.component';

describe('SportClubEventsComponent', () => {
  let component: SportClubEventsComponent;
  let fixture: ComponentFixture<SportClubEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportClubEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportClubEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
