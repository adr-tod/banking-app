import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { User, UserUpdate } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userEndpoint = 'user';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${environment.apiUrl}/${this.userEndpoint}`);
  }

  update(user: UserUpdate): void {
    this.httpClient.post(`${environment.apiUrl}/${this.userEndpoint}/update`, user);
  }
}
