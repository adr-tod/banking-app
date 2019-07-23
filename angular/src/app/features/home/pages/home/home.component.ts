import { Component } from '@angular/core';
import { User } from 'src/app/features/user/models/user.model';
import { UserService } from 'src/app/features/user/services/user.service';
import { AuthenticationService } from 'src/app/features/login/services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  currentUser: User;
  // userFromApi: User;

  constructor(
    private authenticationService: AuthenticationService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  // ngOnInit() {
    // this.userService.findById(this.currentUser.id).pipe(first()).subscribe(user => {
    //   this.userFromApi = user;
    // });
  // }
}

