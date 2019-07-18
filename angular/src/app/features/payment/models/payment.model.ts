
export class Payment {
  id: number;
  debitAccount: string;
  creditAccount: string;
  dateTime: string;
  amount: number;
  currency: string;
  status: string;
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
