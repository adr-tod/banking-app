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
  password: string;
  fullname: string;
  address: string;
  email: string;

  constructor(id: number, password: string, fullname: string, address: string, email: string) {
    this.id = id;
    this.password = password;
    this.fullname = fullname;
    this.address = address;
    this.email = email;
  }
}
