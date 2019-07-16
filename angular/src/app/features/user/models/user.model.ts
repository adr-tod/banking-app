export class User {
  id: number;
  username: string;
  password: string;
  fullname: string;
  address: string;
  email: string;
  profile: string;
}

export class UserUpdate {
  id: number;
  fullname: string;
  address: string;
  email: string;

  constructor(id: number, fullname: string, address: string, email: string) {
    this.id = id;
    this.fullname = fullname;
    this.address = address;
    this.email = email;
  }
}

export class UserAdd {
  fullname: string;
  address: string;
  email: string;
  username: string;
  password: string;

  constructor(fullname: string, address: string, email: string, username: string, password: string) {
    this.fullname = fullname;
    this.address = address;
    this.email = email;
    this.username = username;
    this.password = password;
  }
}
