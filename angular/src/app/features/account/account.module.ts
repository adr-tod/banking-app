import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { AccountComponent } from './pages/account/account.component';
import { AccountTableComponent } from './components/account-table/account-table.component';
import { MatTableModule, MatButtonModule, MatDialogModule, MatInputModule } from '@angular/material';
import { AccountCreateDialogComponent } from './components/account-create-dialog/account-create-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [AccountComponent, AccountTableComponent, AccountCreateDialogComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    MatTableModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatInputModule
  ],
  entryComponents: [
    AccountCreateDialogComponent
  ]
})
export class AccountModule { }
