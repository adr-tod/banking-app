import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { User, UserUpdate, UserAdd } from '../models/user.model';

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

  findById(id: number): Observable<any> {
    return this.httpClient.get<User>(`${environment.apiUrl}/${this.userEndpoint}/find/${id}`);
  }

  add(user: UserAdd): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.userEndpoint}/create`, user);
  }

  update(user: UserUpdate): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.userEndpoint}/update`, user);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.post(`${environment.apiUrl}/${this.userEndpoint}/delete/${id}`, null);
  }
}
