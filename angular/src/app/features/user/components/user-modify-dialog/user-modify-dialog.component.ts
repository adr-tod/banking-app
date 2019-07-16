import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-modify-dialog',
  templateUrl: './user-modify-dialog.component.html',
  styleUrls: ['./user-modify-dialog.component.css']
})
export class UserModifyDialogComponent implements OnInit {

  form: FormGroup;
  user: User;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UserModifyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) user) {

    this.user = user;
  }

  ngOnInit() {
    this.form = this.fb.group({
      id: [{ value: this.user.id, disabled: true }],
      username: [{ value: this.user.username, disabled: true }],
      password: [{ value: this.user.password, disabled: true }],
      fullname: [this.user.fullname],
      address: [this.user.address],
      email: [this.user.email],
      profile: [{ value: this.user.profile, disabled: true }],
    });
  }

  modify() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }
}
