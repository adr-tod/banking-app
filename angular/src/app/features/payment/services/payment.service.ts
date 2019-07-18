import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from '../models/payment.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private paymentEndpoint = 'payment';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Payment[]> {
    return this.httpClient.get<Payment[]>(`${environment.apiUrl}/${this.paymentEndpoint}`);
  }
}
