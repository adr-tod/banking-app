import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatDialogModule, MatIconModule, MatInputModule, MatSnackBarModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import { PaymentCreateDialogComponent } from './components/payment-create-dialog/payment-create-dialog.component';
import { PaymentTableComponent } from './components/payment-table/payment-table.component';
import { PaymentVerifyDialogComponent } from './components/payment-verify-dialog/payment-verify-dialog.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { PaymentRoutingModule } from './payment-routing.module';
import { PaymentPersonalTableComponent } from './components/payment-personal-table/payment-personal-table.component';


@NgModule({
  declarations: [PaymentComponent, PaymentTableComponent, PaymentCreateDialogComponent,
    PaymentVerifyDialogComponent, PaymentPersonalTableComponent],
  imports: [
    CommonModule,
    PaymentRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatIconModule,
    MatSnackBarModule,
    ReactiveFormsModule
  ],
  exports: [
    PaymentPersonalTableComponent
  ],
  entryComponents: [
    PaymentCreateDialogComponent,
    PaymentVerifyDialogComponent
  ]
})
export class PaymentModule { }
