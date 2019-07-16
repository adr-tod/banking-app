import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { UserTableComponent } from './components/user-table/user-table.component';
import { UserModifyDialogComponent } from './components/user-modify-dialog/user-modify-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserAddDialogComponent } from './components/user-add-dialog/user-add-dialog.component';


@NgModule({
  declarations: [UserTableComponent, UserModifyDialogComponent, UserAddDialogComponent],
  exports: [
    UserTableComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule
  ],
  entryComponents: [
    UserAddDialogComponent,
    UserModifyDialogComponent
  ]
})
export class UserModule {
}
