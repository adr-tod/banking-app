import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-payment-verify-dialog',
  templateUrl: './payment-verify-dialog.component.html',
  styleUrls: ['./payment-verify-dialog.component.css']
})
export class PaymentVerifyDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<PaymentVerifyDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private paymentAmount: any) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      amount: [{ value: this.paymentAmount, disabled: true }],
      confirmAmount: ['', [Validators.required, Validators.pattern('^[-+]?[0-9]*\.?[0-9]+$')]]
    });
  }

  verify() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}
