import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportClubUserManagementComponent } from './sportclub-user-management.component';

describe('SportClubUserManagementComponent', () => {
  let component: SportClubUserManagementComponent;
  let fixture: ComponentFixture<SportClubUserManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportClubUserManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportClubUserManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
