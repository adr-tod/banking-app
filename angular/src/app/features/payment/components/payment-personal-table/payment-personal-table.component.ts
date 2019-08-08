import { Component, Input, OnInit } from '@angular/core';
import { Payment } from '../../models/payment.model';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-payment-personal-table',
  templateUrl: './payment-personal-table.component.html',
  styleUrls: ['./payment-personal-table.component.css']
})
export class PaymentPersonalTableComponent implements OnInit {

  displayedColumns: string[] = ['debitAccount', 'creditAccount', 'dateTime', 'amount', 'currency', 'status'];
  dataSource: Payment[];

  @Input()
  accountId: number;

  constructor(private paymentService: PaymentService) {
  }

  ngOnInit(): void {
    this.paymentService.findAllByAccountId(this.accountId).subscribe(data => {
      data.forEach(payment => {
        payment.dateTime = payment.dateTime.replace('T', ' ');
      });
      this.dataSource = data;
    });
  }

}
