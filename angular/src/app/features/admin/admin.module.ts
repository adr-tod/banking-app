import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './pages/admin/admin.component';
import { UserModule } from '../user/user.module';



@NgModule({
  declarations: [AdminComponent],
  imports: [
    CommonModule,
    UserModule
  ]
})
export class AdminModule { }
