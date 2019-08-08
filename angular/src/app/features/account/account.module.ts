import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatDialogModule, MatIconModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatFormFieldModule, MatSelectModule, MatOptionModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccountRoutingModule } from './account-routing.module';
import { AccountCreateDialogComponent } from './components/account-create-dialog/account-create-dialog.component';
import { AccountTableComponent } from './components/account-table/account-table.component';
import { AccountUpdateDialogComponent } from './components/account-update-dialog/account-update-dialog.component';
import { AccountComponent } from './pages/account/account.component';
import { PaymentModule } from '../payment/payment.module';
import { FlexLayoutModule } from '@angular/flex-layout';


@NgModule({
  declarations: [AccountComponent, AccountTableComponent, AccountCreateDialogComponent, AccountUpdateDialogComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSelectModule,
    MatOptionModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    PaymentModule,
    BrowserAnimationsModule,
    FlexLayoutModule
  ],
  entryComponents: [
    AccountCreateDialogComponent,
    AccountUpdateDialogComponent
  ]
})
export class AccountModule { }
