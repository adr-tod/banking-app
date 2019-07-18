import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentComponent } from './pages/payment/payment.component';
import { PaymentTableComponent } from './components/payment-table/payment-table.component';
import { MatTableModule, MatButtonModule } from '@angular/material';


@NgModule({
  declarations: [PaymentComponent, PaymentTableComponent],
  imports: [
    CommonModule,
    PaymentRoutingModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class PaymentModule { }
