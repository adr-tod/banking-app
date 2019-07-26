import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-account-create-dialog',
  templateUrl: './account-create-dialog.component.html',
  styleUrls: ['./account-create-dialog.component.css']
})
export class AccountCreateDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<AccountCreateDialogComponent>) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      name: new FormControl(),
      iban: new FormControl(),
      currency: new FormControl(),
      address: new FormControl(),
      userId: new FormControl()
    });
  }

  create() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}

