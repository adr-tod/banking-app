import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-account-create-dialog',
  templateUrl: './account-create-dialog.component.html',
  styleUrls: ['./account-create-dialog.component.css']
})
export class AccountCreateDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<AccountCreateDialogComponent>) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      iban: ['', Validators.required],
      currency: ['', Validators.required],
      address: ['', Validators.required],
      userId: ['', Validators.required]
    });
  }

  create() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}

