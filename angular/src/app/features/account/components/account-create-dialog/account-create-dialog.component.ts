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
  currencies: string[] = ['EUR', 'USD', 'GBP', 'JPY', 'MXN'];

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<AccountCreateDialogComponent>) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      iban: ['', Validators.required],
      currency: ['', Validators.required],
      address: ['', Validators.required],
      userId: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
    });
  }

  isFieldInvalid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  create() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }

}

