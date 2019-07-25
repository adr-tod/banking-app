import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { PaymentService } from 'src/app/features/payment/services/payment.service';
import { Role } from 'src/app/features/user/models/role.enum';
import { User } from 'src/app/features/user/models/user.model';
import { AccountService } from '../../services/account.service';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { PaymentCreateDialogComponent } from 'src/app/features/payment/components/payment-create-dialog/payment-create-dialog.component';
import { PaymentCreate } from 'src/app/features/payment/models/payment.model';

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
      this.displayedColumns = ['name', 'iban', 'user', 'currency', 'balance', 'status'];
    } else {
      this.accountService.findAllByUserId(this.currentUser.id).subscribe(data => {
        console.log(data);
        this.dataSource = data;
      });
      this.displayedColumns = ['name', 'iban', 'currency', 'balance', 'status', 'actions'];
    }
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
      if (paymentToCreate) {
        console.log(paymentToCreate);
        this.paymentService.create(new PaymentCreate(paymentToCreate.debitIban, paymentToCreate.creditIban, paymentToCreate.amount, paymentToCreate.currency))
          .subscribe(() => { this.ngOnInit(); });
      }
    });
  }
}
