import { User } from '../../user/models/user.model';
import { Account, Currency } from '../../account/models/account.model';

export class Payment {
  id: number;
  debitAccount: Account;
  creditAccount: Account;
  dateTime: string;
  amount: number;
  currency: Currency;
  status: PaymentStatus;
  createdBy: User;
  verifiedBy: User;
}


export class PaymentStatus {
  name: string;
}


export class PaymentCreate {
  debitIban: string;
  creditIban: string;
  amount: number;
  currency: string;

  constructor(debitIban: string, creditIban: string, amount: number, currency: string) {
    this.debitIban = debitIban;
    this.creditIban = creditIban;
    this.amount = amount;
    this.currency = currency;
  }
}


export class PaymentVerify {
  amount: number;

  constructor(amount: number) {
    this.amount = amount;
  }
}
