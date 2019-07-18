import { Account } from '../../account/models/account.model';

export class Payment {
  id: number;
  debitAccount: Account;
  creditAccount: Account;
  dateTime: string;
  amount: number;
  currency: string;
  status: string;
}

export class PaymentCreate {
  debitAccount: string;
  creditAccount: string;
  amount: number;
  currency: string;

  constructor(debitAccount: string, creditAccount: string, amount: number, currency: string) {
    this.debitAccount = debitAccount;
    this.creditAccount = creditAccount;
    this.amount = amount;
    this.currency = currency;
  }
}
