import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-account-table',
  templateUrl: './account-table.component.html',
  styleUrls: ['./account-table.component.css']
})
export class AccountTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'iban', 'name', 'address', 'currency', 'status', 'user'];
  dataSource: Account[];

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.findAll().subscribe(data => {
      console.log(data);
      this.dataSource = data;
    });
  }
}
