import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-user-add-dialog',
  templateUrl: './user-add-dialog.component.html',
  styleUrls: ['./user-add-dialog.component.css']
})
export class UserAddDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<UserAddDialogComponent>) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      fullname: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  add() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }
}
