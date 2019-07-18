import { Component, OnInit } from '@angular/core';
import { Payment } from '../../models/payment.model';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css']
})
export class PaymentTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'debitAccount', 'creditAccount', 'dateTime', 'amount', 'currency', 'status', 'actions'];
  dataSource: Payment[];

  constructor(private paymentService: PaymentService) {
  }

  ngOnInit(): void {
    this.paymentService.findAll().subscribe(data => {
      console.log(data);
      this.dataSource = data;
    });
  }

  // addButtonClicked(): void {
  //   console.log('Add button clicked!');
  //   this.openAddDialog();
  // }

  // openAddDialog(): void {
  //   const dialogConfig = new MatDialogConfig();

  //   dialogConfig.disableClose = true;
  //   dialogConfig.autoFocus = true;

  //   const dialogRef = this.addDialog.open(UserAddDialogComponent, dialogConfig);

  //   dialogRef.afterClosed().subscribe(userToAdd => {
  //     if (userToAdd) {
  //       console.log(userToAdd);
  //       this.userService.add(new UserAdd(userToAdd.fullname, userToAdd.address, userToAdd.email, userToAdd.username, userToAdd.password))
  //         .subscribe(result => { this.ngOnInit(); });
  //     }
  //   });
  // }

  // modifyButtonClicked(user: User): void {
  //   console.log('Modify button clicked!');
  //   this.openModifyDialog(user);
  // }

  // openModifyDialog(user: User): void {
  //   const dialogConfig = new MatDialogConfig();

  //   dialogConfig.disableClose = true;
  //   dialogConfig.autoFocus = true;

  //   dialogConfig.data = user;

  //   const dialogRef = this.modifyDialog.open(UserModifyDialogComponent, dialogConfig);

  //   dialogRef.afterClosed().subscribe(userToUpdate => {
  //     if (userToUpdate) {
  //       console.log(userToUpdate);
  //       this.userService.update(new UserUpdate(userToUpdate.id, userToUpdate.fullname, userToUpdate.address, userToUpdate.email))
  //         .subscribe(() => { this.ngOnInit(); });
  //     }
  //   });
  // }

  // removeButtonClicked(id: number): void {
  //   console.log('Remove button clicked!');
  //   this.userService.delete(id).subscribe(() => { this.ngOnInit(); });
  // }

}