import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { AccountComponent } from './pages/account/account.component';
import { AccountTableComponent } from './components/account-table/account-table.component';
import { MatTableModule, MatButtonModule } from '@angular/material';


@NgModule({
  declarations: [AccountComponent, AccountTableComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class AccountModule { }
