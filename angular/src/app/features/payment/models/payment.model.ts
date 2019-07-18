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
