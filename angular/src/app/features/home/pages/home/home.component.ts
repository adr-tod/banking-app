import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { User } from 'src/app/features/user/models/user.model';
import { UserService } from 'src/app/features/user/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  currentDateTime = new Date();

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
    setInterval(() => {
      this.currentDateTime = new Date();
    }, 1);
  }

  ngOnInit() {
  }
}

