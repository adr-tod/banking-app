import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { User, UserAdd, UserUpdate } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { UserAddDialogComponent } from '../user-add-dialog/user-add-dialog.component';
import { UserModifyDialogComponent } from '../user-modify-dialog/user-modify-dialog.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['username', 'fullname', 'email', 'address', 'actions'];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  constructor(private userService: UserService, private modifyDialog: MatDialog, private addDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.findAll().subscribe(data => {
      this.dataSource.data = data;
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  createButtonClicked(): void {
    this.openCreateDialog();
  }

  openCreateDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.addDialog.open(UserAddDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(userToAdd => {
      if (userToAdd) {
        this.userService.add(new UserAdd(userToAdd.fullname, userToAdd.address, userToAdd.email, userToAdd.username, userToAdd.password))
          .subscribe(result => { this.ngOnInit(); });
      }
    });
  }

  updateButtonClicked(user: User): void {
    this.openUpdateDialog(user);
  }

  openUpdateDialog(user: User): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = user;

    const dialogRef = this.modifyDialog.open(UserModifyDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(userToUpdate => {
      if (userToUpdate) {
        this.userService.update(new UserUpdate(userToUpdate.id, userToUpdate.fullname, userToUpdate.address, userToUpdate.email))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  deleteButtonClicked(id: number): void {
    this.userService.delete(id).subscribe(() => { this.ngOnInit(); });
  }
}
