import { User } from '../../user/models/user.model';

export class Account {
  id: number;
  iban: string;
  name: string;
  address: string;
  currency: Currency;
  status: AccountStatus;
  user: User;
  balance: Balance;
}

export class AccountStatus {
  name: string;
}


export class Balance {
  available: number;
}


export class Currency {
  name: string;
}


export class AccountCreate {
  name: string;
  iban: string;
  currency: string;
  address: string;
  userId: number;

  constructor(name: string, iban: string, currency: string, address: string, userId: number) {
    this.name = name;
    this.iban = iban;
    this.currency = currency;
    this.address = address;
    this.userId = userId;
  }
}


export class AccountUpdate {
  id: number;
  name: string;
  address: string;
  balance: number;
  status: string;

  constructor(id: number, name: string, address: string, balance: number, status: string) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.balance = balance;
    this.status = status;
  }
}
