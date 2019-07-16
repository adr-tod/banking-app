import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { UserModifyDialogComponent } from '../user-modify-dialog/user-modify-dialog.component';

@Component({
  selector: 'app-user-add-dialog',
  templateUrl: './user-add-dialog.component.html',
  styleUrls: ['./user-add-dialog.component.css']
})
export class UserAddDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<UserAddDialogComponent>) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      fullname: new FormControl({value: 'asd', disabled: true}),
      address: new FormControl(),
      email: new FormControl(),
      username: new FormControl(),
      password: new FormControl()
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

  add() {
    this.dialogRef.close(this.form.value);
  }

  close() {
    this.dialogRef.close();
  }
}
