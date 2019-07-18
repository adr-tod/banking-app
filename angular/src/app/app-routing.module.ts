import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './features/user/pages/user/user.component';
import { AccountComponent } from './features/account/pages/account/account.component';
import { PaymentComponent } from './features/payment/pages/payment/payment.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/user',
    pathMatch: 'full'
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
