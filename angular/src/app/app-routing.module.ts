import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './features/user/pages/user/user.component';
import { AccountComponent } from './features/account/pages/account/account.component';
import { PaymentComponent } from './features/payment/pages/payment/payment.component';
import { HomeComponent } from './features/home/pages/home/home.component';
import { AuthGuard } from './core/guards/auth.guard';
import { Role } from './features/user/models/role.enum';
import { LoginComponent } from './features/login/pages/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.ADMIN] }
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'payment',
    component: PaymentComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.ADMIN] }
  },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
