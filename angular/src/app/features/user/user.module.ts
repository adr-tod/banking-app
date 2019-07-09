import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {MatTableModule, MatPaginatorModule, MatSortModule} from '@angular/material';
import { UserTableComponent } from './components/user-table/user-table.component';


@NgModule({
  declarations: [UserTableComponent],
  exports: [
    UserTableComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ]
})
export class UserModule {
}
