import { Component, OnInit } from '@angular/core';
import { Payment, PaymentCreate, PaymentVerify } from '../../models/payment.model';
import { PaymentService } from '../../services/payment.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { PaymentCreateDialogComponent } from '../payment-create-dialog/payment-create-dialog.component';
import { PaymentVerifyDialogComponent } from '../payment-verify-dialog/payment-verify-dialog.component';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css']
})
export class PaymentTableComponent implements OnInit {

  displayedColumns: string[] = ['debitAccount', 'creditAccount', 'dateTime', 'amount', 'currency', 'status', 'actions'];
  dataSource: Payment[];

  constructor(private paymentService: PaymentService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.paymentService.findAll().subscribe(data => {
      this.dataSource = data;
    });
  }

  isPaymentStatusEqualTo(payment: Payment, status: string) {
    return payment.status.name === status;
  }

  paymentCreateButtonClicked(): void {
    this.openPaymentCreateDialog();
  }

  openPaymentCreateDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(PaymentCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToCreate => {
      if (paymentToCreate) {
        this.paymentService.create(new PaymentCreate(paymentToCreate.debitIban, paymentToCreate.creditIban,
          paymentToCreate.amount, paymentToCreate.currency))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  paymentVerifyButtonClicked(payment: Payment) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = payment.amount;

    const dialogRef = this.dialog.open(PaymentVerifyDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToVerify => {
      if (paymentToVerify) {
        this.paymentService.verify(payment.id, new PaymentVerify(paymentToVerify.confirmAmount))
          .subscribe(result => { console.log(result); this.ngOnInit(); });
      }
    });
  }

  paymentApproveButtonClicked(id: number) {
    this.paymentService.approve(id)
      .subscribe(() => { this.ngOnInit(); });
  }

  paymentAuthorizeButtonClicked(id: number) {
    this.paymentService.authorize(id)
      .subscribe(() => { this.ngOnInit(); });
  }

  paymentCancelButtonClicked(id: number) {
    this.paymentService.cancel(id)
      .subscribe(() => { this.ngOnInit(); });
  }

}
