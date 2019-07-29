import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-payment-verify-dialog',
  templateUrl: './payment-verify-dialog.component.html',
  styleUrls: ['./payment-verify-dialog.component.css']
})
export class PaymentVerifyDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<PaymentVerifyDialogComponent>, @Inject(MAT_DIALOG_DATA) private paymentAmount: any) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      amount: new FormControl({ value: this.paymentAmount, disabled: true }),
      confirmAmount: new FormControl()
    });
  }

  verify() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}
