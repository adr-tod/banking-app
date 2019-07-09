import {Injectable} from '@angular/core';
import {ApiService} from '../../../core/http/api.service';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {User} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersEndpoint = 'user';

  constructor(private apiService: ApiService) {
  }

  findAll(): User[] {
    return this.apiService.get(`${environment.baseUrl}/${this.usersEndpoint}`);
  }
}
