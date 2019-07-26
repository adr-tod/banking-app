import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountUpdateDialogComponent } from './account-update-dialog.component';

describe('AccountUpdateDialogComponent', () => {
  let component: AccountUpdateDialogComponent;
  let fixture: ComponentFixture<AccountUpdateDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountUpdateDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
