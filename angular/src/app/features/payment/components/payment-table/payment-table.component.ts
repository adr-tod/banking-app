import { Component, OnInit } from '@angular/core';
import { Payment, PaymentCreate } from '../../models/payment.model';
import { PaymentService } from '../../services/payment.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { PaymentCreateDialogComponent } from '../payment-create-dialog/payment-create-dialog.component';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css']
})
export class PaymentTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'debitAccount', 'creditAccount', 'dateTime', 'amount', 'currency', 'status', 'actions'];
  dataSource: Payment[];

  constructor(private paymentService: PaymentService, private paymentCreateDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.paymentService.findAll().subscribe(data => {
      console.log(data);
      this.dataSource = data;
    });
  }

  paymentCreateButtonClicked(): void {
    console.log('Create payment button clicked!');
    this.openPaymentCreateDialog();
  }

  openPaymentCreateDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.paymentCreateDialog.open(PaymentCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToCreate => {
      if (paymentToCreate) {
        console.log(paymentToCreate);
        this.paymentService.create(new PaymentCreate(paymentToCreate.debitIban, paymentToCreate.creditIban, paymentToCreate.amount, paymentToCreate.currency))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  paymentVerifyButtonClicked(id: number) {
    this.paymentService.verify(id)
      .subscribe(() => { this.ngOnInit(); })
  }

  paymentApproveButtonClicked(id: number) {
    this.paymentService.approve(id)
      .subscribe(() => { this.ngOnInit(); })
  }

  paymentAuthorizeButtonClicked(id: number) {
    this.paymentService.authorize(id)
      .subscribe(() => { this.ngOnInit(); })
  }

  paymentCancelButtonClicked(id: number) {
    this.paymentService.cancel(id)
      .subscribe(() => { this.ngOnInit(); })
  }

}
