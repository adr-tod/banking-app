import { Component } from '@angular/core';
import { User } from './features/user/models/user.model';
import { Router } from '@angular/router';
import { AuthenticationService } from './features/login/services/authentication.service';
import { Role } from './features/user/models/role.enum';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
