import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { User, UserProfile } from '../../models/user.model';

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
      id: [this.user.id],
      username: [{ value: this.user.username, disabled: true }],
      fullname: [this.user.fullname, Validators.required],
      address: [this.user.address, Validators.required],
      email: [this.user.email, [Validators.required, Validators.email]],
      profile: [{ value: (this.user.profile as unknown as UserProfile).name, disabled: true }],
    });
  }

  isFieldInvalid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  modify() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }
}
