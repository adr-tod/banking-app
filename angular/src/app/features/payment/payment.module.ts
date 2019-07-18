import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentComponent } from './pages/payment/payment.component';
import { PaymentTableComponent } from './components/payment-table/payment-table.component';
import { MatTableModule, MatButtonModule, MatDialogModule, MatInputModule } from '@angular/material';
import { PaymentCreateDialogComponent } from './components/payment-create-dialog/payment-create-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [PaymentComponent, PaymentTableComponent, PaymentCreateDialogComponent],
  imports: [
    CommonModule,
    PaymentRoutingModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    PaymentCreateDialogComponent
  ]
})
export class PaymentModule { }
