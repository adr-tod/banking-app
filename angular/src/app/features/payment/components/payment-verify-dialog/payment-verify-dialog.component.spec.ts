import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentVerifyDialogComponent } from './payment-verify-dialog.component';

describe('PaymentVerifyDialogComponent', () => {
  let component: PaymentVerifyDialogComponent;
  let fixture: ComponentFixture<PaymentVerifyDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentVerifyDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentVerifyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
