import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatDialogModule, MatFormFieldModule, MatIconModule, MatInputModule, MatOptionModule, MatPaginatorModule, MatSelectModule, MatSnackBarModule, MatSortModule, MatTableModule, MatAutocompleteModule } from '@angular/material';
import { PaymentCreateDialogComponent } from './components/payment-create-dialog/payment-create-dialog.component';
import { PaymentPersonalTableComponent } from './components/payment-personal-table/payment-personal-table.component';
import { PaymentTableComponent } from './components/payment-table/payment-table.component';
import { PaymentVerifyDialogComponent } from './components/payment-verify-dialog/payment-verify-dialog.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { PaymentRoutingModule } from './payment-routing.module';


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
    MatSelectModule,
    MatOptionModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatAutocompleteModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    FlexLayoutModule
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
