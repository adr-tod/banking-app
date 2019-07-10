import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { AccountComponent } from './pages/account/account.component';
import { AccountTableComponent } from './components/account-table/account-table.component';
import { MatTableModule } from '@angular/material';


@NgModule({
  declarations: [AccountComponent, AccountTableComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    MatTableModule
  ]
})
export class AccountModule { }
