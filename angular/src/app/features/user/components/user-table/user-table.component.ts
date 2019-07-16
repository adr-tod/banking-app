import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User, UserUpdate } from '../../models/user.model';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { UserModifyDialogComponent } from '../user-modify-dialog/user-modify-dialog.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'username', 'fullname', 'email', 'address', 'actions'];
  dataSource: User[];

  constructor(private userService: UserService, private modifyDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.findAll().subscribe(data => {
      this.dataSource = data;
    });
  }

  modifyButtonClicked(user: User): void {
    console.log("Modify button clicked!");
    this.openModifyDialog(user);
  }

  openModifyDialog(user: User): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = user;

    const dialogRef = this.modifyDialog.open(UserModifyDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(updatedUser => {
      console.log(updatedUser);
      this.userService.update(new UserUpdate(updatedUser.id, updatedUser.password, updatedUser.fullname, updatedUser.address, updatedUser.email));
    });
}

removeButtonClicked(): void {
  console.log("Remove button clicked!");
}
}
