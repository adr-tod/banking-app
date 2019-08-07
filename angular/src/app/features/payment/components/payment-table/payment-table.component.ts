import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig, MatSnackBar, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { User } from 'src/app/features/user/models/user.model';
import { Payment, PaymentCreate, PaymentVerify } from '../../models/payment.model';
import { PaymentService } from '../../services/payment.service';
import { PaymentCreateDialogComponent } from '../payment-create-dialog/payment-create-dialog.component';
import { PaymentVerifyDialogComponent } from '../payment-verify-dialog/payment-verify-dialog.component';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css']
})
export class PaymentTableComponent implements OnInit, AfterViewInit {

  currentUser: User;
  displayedColumns: string[] = ['debitAccount', 'creditAccount', 'dateTime', 'amount', 'currency', 'status', 'actions'];
  dataSource = new MatTableDataSource<Payment>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  constructor(private authenticationService: AuthenticationService, private paymentService: PaymentService,
    private dialog: MatDialog, private snackbar: MatSnackBar) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.paymentService.findAll().subscribe(data => {
      this.dataSource.data = data;
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    // sorting accesor for handling nested objects sorting
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
        case 'debitAccount': return item.debitAccount.iban;
        case 'creditAccount': return item.creditAccount.iban;
        case 'currency': return item.currency.name;
        case 'status': return item.status.name;
        default: return item[property];
      }
    };
    // filter predicate for handling nested objects filtering
    this.dataSource.filterPredicate = (data, filter: string) => {
      const accumulator = (currentTerm, key) => {
        switch (key) {
          case 'debitAccount': return currentTerm + data.debitAccount.iban;
          case 'creditAccount': return currentTerm + data.creditAccount.iban;
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

  isPaymentStatusEqualTo(payment: Payment, status: string) {
    return payment.status.name === status;
  }

  isPaymentCreatedBy(payment: Payment, user: User) {
    return payment.createdBy.id === user.id;
  }

  isPaymentVerifiedBy(payment: Payment, user: User) {
    return payment.verifiedBy.id === user.id;
  }

  paymentCreateButtonClicked(): void {
    this.openPaymentCreateDialog();
  }

  openPaymentCreateDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

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

  paymentVerifyButtonClicked(payment: Payment) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = payment.amount;

    const dialogRef = this.dialog.open(PaymentVerifyDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(paymentToVerify => {
      if (paymentToVerify) {
        this.paymentService.verify(payment.id, new PaymentVerify(paymentToVerify.confirmAmount))
          .subscribe(
            () => { this.ngOnInit(); },
            error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
      }
    });
  }

  paymentApproveButtonClicked(id: number) {
    this.paymentService.approve(id)
      .subscribe(
        () => { this.ngOnInit(); },
        error => { this.snackbar.open('Error: ' + error, null, { duration: 7500 }); this.ngOnInit(); });
  }

  paymentAuthorizeButtonClicked(id: number) {
    this.paymentService.authorize(id)
      .subscribe(
        () => { this.ngOnInit(); },
        error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
  }

  paymentCancelButtonClicked(id: number) {
    this.paymentService.cancel(id)
      .subscribe(
        () => { this.ngOnInit(); },
        error => { this.snackbar.open('Error: ' + error, null, { duration: 5000 }); });
  }

}
