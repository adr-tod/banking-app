import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-payment-create-dialog',
  templateUrl: './payment-create-dialog.component.html',
  styleUrls: ['./payment-create-dialog.component.css']
})
export class PaymentCreateDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<PaymentCreateDialogComponent>, @Inject(MAT_DIALOG_DATA) public debitIban: any) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      debitIban: new FormControl(),
      creditIban: new FormControl(),
      amount: new FormControl(),
      currency: new FormControl()
    });

    if (this.debitIban) {
      this.form.setControl('debitIban', new FormControl({ value: this.debitIban, disabled: true }));
    }
  }

  create() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }

}
