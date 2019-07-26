export class Account {
  id: number;
  iban: string;
  name: string;
  address: string;
  currency: string;
  status: string;
  user: string;
  balance: number;
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
