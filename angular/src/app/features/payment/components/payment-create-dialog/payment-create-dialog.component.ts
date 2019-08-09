import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AccountService } from 'src/app/features/account/services/account.service';
import { Account } from 'src/app/features/account/models/account.model';
import { startWith, map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-payment-create-dialog',
  templateUrl: './payment-create-dialog.component.html',
  styleUrls: ['./payment-create-dialog.component.css']
})
export class PaymentCreateDialogComponent implements OnInit {

  form: FormGroup;
  currencies: string[] = ['EUR', 'USD', 'GBP', 'JPY', 'MXN'];
  options: string[] = [];
  filteredOptionsDebit: Observable<string[]>;
  filteredOptionsCredit: Observable<string[]>;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<PaymentCreateDialogComponent>, private accountService: AccountService,
    @Inject(MAT_DIALOG_DATA) public debitIban: any) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      debitIban: ['', Validators.required],
      creditIban: ['', Validators.required],
      amount: ['', [Validators.required, Validators.pattern('^[-+]?[0-9]*\.?[0-9]+$')]],
      currency: ['', Validators.required]
    });
    if (this.debitIban) {
      this.form.setControl('debitIban', new FormControl({ value: this.debitIban, disabled: true }));
    }
    this.accountService.findAll().subscribe(accounts => {
      accounts.forEach(account => {
        this.options.push(account.iban);
      });
    });
    // autocomplete
    this.filteredOptionsDebit = this.form.get('debitIban').valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    this.filteredOptionsCredit = this.form.get('creditIban').valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  // autocomplete
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }

  isFieldInvalid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  create() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }

}
