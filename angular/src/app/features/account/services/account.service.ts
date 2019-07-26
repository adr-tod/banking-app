import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Account } from '../models/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountEndpoint = 'account';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(`${environment.apiUrl}/${this.accountEndpoint}`);
  }

  findAllByUserId(id: number): Observable<Account[]> {
    return this.httpClient.get<Account[]>(`${environment.apiUrl}/${this.accountEndpoint}/findall/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.accountEndpoint}/delete/${id}`, null);
  }
}
