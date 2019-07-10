import {Injectable} from '@angular/core';
import {ApiService} from '../../../core/http/api.service';
import {environment} from '../../../../environments/environment';
import {User} from '../models/user.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersEndpoint = 'user';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<User[]> {
    // return this.apiService.get(`${environment.baseUrl}/${this.usersEndpoint}`);
    return this.httpClient.get<User[]>(`${environment.baseUrl}/${this.usersEndpoint}`);
  }
}
