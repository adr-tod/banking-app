import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './features/user/pages/user/user.component';
import { AccountComponent } from './features/account/pages/account/account.component';
import { PaymentComponent } from './features/payment/pages/payment/payment.component';
import { HomeComponent } from './features/home/pages/home/home.component';
import { AuthGuard } from './core/guards/auth.guard';
import { AdminComponent } from './features/admin/pages/admin/admin.component';
import { Role } from './features/user/models/role.enum';
import { LoginComponent } from './features/login/pages/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin] }
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user',
    component: UserComponent,
  },
  {
    path: 'account',
    component: AccountComponent,
  },
  {
    path: 'payment',
    component: PaymentComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
