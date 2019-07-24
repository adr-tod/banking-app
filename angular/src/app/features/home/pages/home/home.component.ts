import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/features/user/models/user.model';
import { UserService } from 'src/app/features/user/services/user.service';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentUser: User;
  userFromApi: User;

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
    console.log(this.currentUser);
  }

  ngOnInit() {
    this.userService.findById(this.currentUser.id).pipe(first()).subscribe(user => {
      console.log(user);
      this.userFromApi = user;
    });
  }
}

