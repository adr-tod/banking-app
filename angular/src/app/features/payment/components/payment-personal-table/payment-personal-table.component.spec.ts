import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentPersonalTableComponent } from './payment-personal-table.component';

describe('PaymentPersonalTableComponent', () => {
  let component: PaymentPersonalTableComponent;
  let fixture: ComponentFixture<PaymentPersonalTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentPersonalTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentPersonalTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
