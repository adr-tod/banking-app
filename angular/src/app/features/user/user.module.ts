import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatDialogModule, MatFormFieldModule, MatIconModule, MatInputModule, MatPaginatorModule, MatSortModule, MatTableModule } from '@angular/material';
import { UserAddDialogComponent } from './components/user-add-dialog/user-add-dialog.component';
import { UserModifyDialogComponent } from './components/user-modify-dialog/user-modify-dialog.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { UserRoutingModule } from './user-routing.module';



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
    MatInputModule,
    MatIconModule
  ],
  entryComponents: [
    UserAddDialogComponent,
    UserModifyDialogComponent
  ]
})
export class UserModule {
}
