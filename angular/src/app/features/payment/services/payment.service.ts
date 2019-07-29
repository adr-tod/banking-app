import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment, PaymentCreate, PaymentVerify } from '../models/payment.model';
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

  create(payment: PaymentCreate): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.paymentEndpoint}/create`, payment);
  }

  verify(id: number, confirmAmount: PaymentVerify): Observable<any> {
    console.log(confirmAmount);
    return this.httpClient.post(`${environment.apiUrl}/${this.paymentEndpoint}/verify/${id}`, confirmAmount);
  }

  approve(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.paymentEndpoint}/approve/${id}`, null);
  }

  authorize(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.paymentEndpoint}/authorize/${id}`, null);
  }

  cancel(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.paymentEndpoint}/cancel/${id}`, null);
  }

}
