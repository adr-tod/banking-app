import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-payment-create-dialog',
  templateUrl: './payment-create-dialog.component.html',
  styleUrls: ['./payment-create-dialog.component.css']
})
export class PaymentCreateDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<PaymentCreateDialogComponent>) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      debitIban: new FormControl(),
      creditIban: new FormControl(),
      amount: new FormControl(),
      currency: new FormControl()
    });
    // this.form = this.fb.group({
    //   id: [{ value: this.user.id, disabled: true }],
    //   username: [{ value: this.user.username, disabled: true }],
    //   password: [{ value: this.user.password, disabled: true }],
    //   fullname: [this.user.fullname],
    //   address: [this.user.address],
    //   email: [this.user.email],
    //   profile: [{ value: this.user.profile, disabled: true }],
    // });
  }

  create() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}
