package com.montran.banking.account.business;

import com.montran.banking.account.domain.entity.Account;

public interface AccountService {

	public Iterable<Account> findAll();
}
