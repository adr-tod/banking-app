import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
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
  accountStatuses: string[] = ['ACTIVE', 'BLOCKED', 'BLOCKED_DEBIT', 'BLOCKED_CREDIT', 'CLOSED'];

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<AccountUpdateDialogComponent>, @Inject(MAT_DIALOG_DATA) account) {
    this.account = account;
  }

  ngOnInit() {
    this.form = this.fb.group({
      id: [this.account.id],
      name: [this.account.name, Validators.required],
      iban: [{ value: this.account.iban, disabled: true }],
      user: [{ value: this.account.user.username, disabled: true }],
      currency: [{ value: this.account.currency.name, disabled: true }],
      address: [this.account.address, Validators.required],
      balance: [this.account.balance.available, [Validators.required, Validators.pattern('^[-+]?[0-9]*\.?[0-9]+$')]],
      status: [this.account.status.name, Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  update() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }

}
