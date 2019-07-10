import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountEndpoint = 'account';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(`${environment.baseUrl}/${this.accountEndpoint}`);
  }
}
