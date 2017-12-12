import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportclubEventCreationComponent } from './sportclub-event-creation.component';

describe('SportclubEventCreationComponent', () => {
  let component: SportclubEventCreationComponent;
  let fixture: ComponentFixture<SportclubEventCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportclubEventCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportclubEventCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
