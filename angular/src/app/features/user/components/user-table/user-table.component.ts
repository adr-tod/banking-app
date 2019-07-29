import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { User, UserAdd, UserUpdate } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { UserAddDialogComponent } from '../user-add-dialog/user-add-dialog.component';
import { UserModifyDialogComponent } from '../user-modify-dialog/user-modify-dialog.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  displayedColumns: string[] = ['username', 'fullname', 'email', 'address', 'actions'];
  dataSource: User[];

  constructor(private userService: UserService, private modifyDialog: MatDialog, private addDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.findAll().subscribe(data => {
      this.dataSource = data;
    });
  }

  addButtonClicked(): void {
    console.log('Add button clicked!');
    this.openAddDialog();
  }

  openAddDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.addDialog.open(UserAddDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(userToAdd => {
      if (userToAdd) {
        console.log(userToAdd);
        this.userService.add(new UserAdd(userToAdd.fullname, userToAdd.address, userToAdd.email, userToAdd.username, userToAdd.password))
          .subscribe(result => { this.ngOnInit(); });
      }
    });
  }

  modifyButtonClicked(user: User): void {
    console.log('Modify button clicked!');
    this.openModifyDialog(user);
  }

  openModifyDialog(user: User): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = user;

    const dialogRef = this.modifyDialog.open(UserModifyDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(userToUpdate => {
      if (userToUpdate) {
        console.log(userToUpdate);
        this.userService.update(new UserUpdate(userToUpdate.id, userToUpdate.fullname, userToUpdate.address, userToUpdate.email))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  removeButtonClicked(id: number): void {
    console.log('Remove button clicked!');
    this.userService.delete(id).subscribe(() => { this.ngOnInit(); });
  }
}
