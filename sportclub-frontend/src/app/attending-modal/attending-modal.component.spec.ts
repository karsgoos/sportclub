import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendingModalComponent } from './attending-modal.component';

describe('AttendingModalComponent', () => {
  let component: AttendingModalComponent;
  let fixture: ComponentFixture<AttendingModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendingModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendingModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
