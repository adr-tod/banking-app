import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Account, AccountCreate, AccountUpdate } from '../models/account.model';

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

  create(account: AccountCreate): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.accountEndpoint}/create`, account);
  }

  update(account: AccountUpdate): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.accountEndpoint}/update`, account);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.accountEndpoint}/delete/${id}`, null);
  }
}
