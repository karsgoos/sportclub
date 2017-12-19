import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancellationsModalComponent } from './cancellations-modal.component';

describe('CancellationsModalComponent', () => {
  let component: CancellationsModalComponent;
  let fixture: ComponentFixture<CancellationsModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancellationsModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancellationsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
