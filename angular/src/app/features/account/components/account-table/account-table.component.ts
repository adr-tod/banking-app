import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { PaymentCreateDialogComponent } from 'src/app/features/payment/components/payment-create-dialog/payment-create-dialog.component';
import { PaymentCreate } from 'src/app/features/payment/models/payment.model';
import { PaymentService } from 'src/app/features/payment/services/payment.service';
import { Role } from 'src/app/features/user/models/role.enum';
import { User } from 'src/app/features/user/models/user.model';
import { AccountService } from '../../services/account.service';
import { Account, AccountCreate } from '../../models/account.model';
import { AccountCreateDialogComponent } from '../account-create-dialog/account-create-dialog.component';

@Component({
  selector: 'app-account-table',
  templateUrl: './account-table.component.html',
  styleUrls: ['./account-table.component.css']
})
export class AccountTableComponent implements OnInit {

  currentUser: User;
  displayedColumns: string[];
  dataSource: Account[];

  constructor(private authenticationService: AuthenticationService, private accountService: AccountService,
              private paymentService: PaymentService, private paymentCreateDialog: MatDialog) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    if (this.currentUser.profile === Role.ADMIN) {
      this.accountService.findAll().subscribe(data => {
        console.log(data);
        this.dataSource = data;
      });
      this.displayedColumns = ['name', 'iban', 'user', 'currency', 'balance', 'status', 'actions'];
    } else {
      this.accountService.findAllByUserId(this.currentUser.id).subscribe(data => {
        console.log(data);
        this.dataSource = data;
      });
      this.displayedColumns = ['name', 'iban', 'currency', 'balance', 'status', 'actions'];
    }
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.profile === Role.ADMIN;
  }

  get isCustomer() {
    return this.currentUser && this.currentUser.profile === Role.CUSTOMER;
  }

  makePaymentButtonClicked(debitIban: number) {
    console.log('Make payment button clicked!');
    this.openPaymentCreateDialog(debitIban);
  }

  openPaymentCreateDialog(debitIban: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = debitIban;

    const dialogRef = this.paymentCreateDialog.open(PaymentCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToCreate => {
      console.log(paymentToCreate);
      if (paymentToCreate) {
        console.log(paymentToCreate);
        this.paymentService.create(new PaymentCreate(paymentToCreate.debitIban, paymentToCreate.creditIban, paymentToCreate.amount, paymentToCreate.currency))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  createAccountButtonClicked() {
    this.openAccountCreateDialog();
  }

  openAccountCreateDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.paymentCreateDialog.open(AccountCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(accountToCreate => {
      console.log(accountToCreate);
      if (accountToCreate) {
        console.log(accountToCreate);
        this.accountService.create(new AccountCreate(accountToCreate.name, accountToCreate.iban,
          accountToCreate.currency, accountToCreate.address, accountToCreate.userId))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }

  updateAccountButtonClicked(account: Account) {
    console.log('Update account');
  }

  deleteAccountButtonClicked(id: number) {
    this.accountService.delete(id).subscribe(() => { this.ngOnInit(); });
  }
}
