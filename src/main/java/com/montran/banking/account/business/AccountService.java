package com.montran.banking.account.business;

import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.dto.AccountUpdateDTO;
import com.montran.banking.account.domain.entity.Account;

public interface AccountService {

	public Account findById(Long id);
	
	public Account findByIban(String iban);

	public Iterable<Account> findAll();

	public Iterable<Account> findAllByUserId(Long id);

	public Account create(AccountCreateDTO accountCreateDTO);

	public Account update(AccountUpdateDTO accountUpdateDTO);

	public void deleteById(Long id);

	public void deleteByIban(String iban);
}
