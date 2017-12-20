import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleManagementPanelComponent } from './role-management-panel.component';

describe('RoleManagementPanelComponent', () => {
  let component: RoleManagementPanelComponent;
  let fixture: ComponentFixture<RoleManagementPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoleManagementPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoleManagementPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
