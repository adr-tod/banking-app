import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-payment-create-dialog',
  templateUrl: './payment-create-dialog.component.html',
  styleUrls: ['./payment-create-dialog.component.css']
})
export class PaymentCreateDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<PaymentCreateDialogComponent>,
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
