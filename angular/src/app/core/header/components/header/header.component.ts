import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { Router } from '@angular/router';
import { User } from 'src/app/features/user/models/user.model';
import { Role } from 'src/app/features/user/models/role.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser: User;

  constructor(private router: Router, private authenticationService: AuthenticationService) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.profile === Role.ADMIN;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
