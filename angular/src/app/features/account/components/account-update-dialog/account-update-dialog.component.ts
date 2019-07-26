import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Account } from '../../models/account.model';

@Component({
  selector: 'app-account-update-dialog',
  templateUrl: './account-update-dialog.component.html',
  styleUrls: ['./account-update-dialog.component.css']
})
export class AccountUpdateDialogComponent implements OnInit {

  form: FormGroup;
  account: Account;

  constructor(private dialogRef: MatDialogRef<AccountUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) account) {
    console.log(account);
    this.account = account;
  }

  ngOnInit() {
    this.form = new FormGroup({
      id: new FormControl({ value: this.account.id, disabled: true }),
      name: new FormControl(),
      iban: new FormControl({ value: this.account.iban, disabled: true }),
      user: new FormControl({ value: this.account.user.username, disabled: true }),
      currency: new FormControl({ value: this.account.currency.name, disabled: true }),
      address: new FormControl(),
      balance: new FormControl(),
      status: new FormControl()
    });
  }

  update() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }

}
