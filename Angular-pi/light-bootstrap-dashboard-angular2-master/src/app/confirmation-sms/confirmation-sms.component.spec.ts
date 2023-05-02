import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationSMSComponent } from './confirmation-sms.component';

describe('ConfirmationSMSComponent', () => {
  let component: ConfirmationSMSComponent;
  let fixture: ComponentFixture<ConfirmationSMSComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmationSMSComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmationSMSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
