import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog, MatDialogConfig, MatSnackBar, MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { PaymentCreateDialogComponent } from 'src/app/features/payment/components/payment-create-dialog/payment-create-dialog.component';
import { PaymentCreate } from 'src/app/features/payment/models/payment.model';
import { PaymentService } from 'src/app/features/payment/services/payment.service';
import { Role } from 'src/app/features/user/models/role.enum';
import { User } from 'src/app/features/user/models/user.model';
import { AccountService } from '../../services/account.service';
import { Account, AccountCreate, AccountUpdate } from '../../models/account.model';
import { AccountCreateDialogComponent } from '../account-create-dialog/account-create-dialog.component';
import { AccountUpdateDialogComponent } from '../account-update-dialog/account-update-dialog.component';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-account-table',
  templateUrl: './account-table.component.html',
  styleUrls: ['./account-table.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', display: 'none' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class AccountTableComponent implements OnInit, AfterViewInit {

  currentUser: User;
  displayedColumns: string[];
  dataSource = new MatTableDataSource<Account>();

  expandedElement: any;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  constructor(private authenticationService: AuthenticationService, private accountService: AccountService,
    private paymentService: PaymentService, private dialog: MatDialog, private snackbar: MatSnackBar) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    if (this.currentUser.profile === Role.ADMIN) {
      this.accountService.findAll().subscribe(data => {
        this.dataSource.data = data;
      });
      this.displayedColumns = ['name', 'iban', 'username', 'currency', 'balance', 'status', 'actions'];
    } else {
      this.accountService.findAllByUserId(this.currentUser.id).subscribe(data => {
        this.dataSource.data = data;
      });
      this.displayedColumns = ['name', 'iban', 'currency', 'balance', 'status', 'actions'];
    }
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    // sorting accessor for handling nested objects sorting
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
        case 'username': return item.user.username;
        case 'balance': return item.balance.available;
        case 'currency': return item.currency.name;
        case 'status': return item.status.name;
        default: return item[property];
      }
    };
    // filter predicate for handling nested objects filtering
    this.dataSource.filterPredicate = (data, filter: string) => {
      const accumulator = (currentTerm, key) => {
        switch (key) {
          case 'username': return currentTerm + data.user.username;
          case 'balance': return currentTerm + data.balance.available;
          case 'currency': return currentTerm + data.currency.name;
          case 'status': return currentTerm + data.status.name;
          default: return currentTerm + data[key];
        }
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      // Transform the filter by converting it to lowercase and removing whitespace
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.profile === Role.ADMIN;
  }

  get isCustomer() {
    return this.currentUser && this.currentUser.profile === Role.CUSTOMER;
  }

  makePaymentButtonClicked(debitIban: number) {
    this.openPaymentCreateDialog(debitIban);
  }

  openPaymentCreateDialog(debitIban: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '305px';
    dialogConfig.data = debitIban;

    const dialogRef = this.dialog.open(PaymentCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToCreate => {
      if (paymentToCreate) {
        this.paymentService.create(new PaymentCreate(paymentToCreate.debitIban, paymentToCreate.creditIban,
          paymentToCreate.amount, paymentToCreate.currency))
          .subscribe(
            () => { this.ngOnInit(); },
            error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
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

    const dialogRef = this.dialog.open(AccountCreateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(accountToCreate => {
      if (accountToCreate) {
        this.accountService.create(new AccountCreate(accountToCreate.name, accountToCreate.iban,
          accountToCreate.currency, accountToCreate.address, accountToCreate.userId))
          .subscribe(
            () => { this.ngOnInit(); },
            error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
      }
    });
  }

  updateAccountButtonClicked(account: Account) {
    this.openAccountUpdateDialog(account);
  }

  openAccountUpdateDialog(account: Account) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = account;

    const dialogRef = this.dialog.open(AccountUpdateDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(accountToUpdate => {
      if (accountToUpdate) {
        this.accountService.update(new AccountUpdate(accountToUpdate.id, accountToUpdate.name,
          accountToUpdate.address, accountToUpdate.balance, accountToUpdate.status))
          .subscribe(
            () => { this.ngOnInit(); },
            error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
      }
    });
  }

  deleteAccountButtonClicked(id: number) {
    this.accountService.delete(id).subscribe(() => { this.ngOnInit(); });
  }
}
