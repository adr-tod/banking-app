package com.montran.banking.account.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.accountstatus.persistence.AccountStatusRepository;
import com.montran.banking.balance.domain.entity.Balance;
import com.montran.banking.balance.persistence.BalanceRepository;
import com.montran.banking.currency.persistence.CurrencyRepository;
import com.montran.banking.user.persistence.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountStatusRepository accountStatusRepository;

	@Autowired
	private BalanceRepository balanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Iterable<Account> findAllByUserId(Long id) {
		return accountRepository.findAllByUserId(id);
	}

	@Override
	public Account create(AccountCreateDTO accountCreateDTO) {
		Account account = new Account();
		account.setName(accountCreateDTO.getName());
		account.setIban(accountCreateDTO.getIban());
		account.setCurrency(currencyRepository.findByName(accountCreateDTO.getCurrency()));
		account.setAddress(accountCreateDTO.getAddress());
		account.setUser(userRepository.findById(accountCreateDTO.getUserId()).get());
		account.setBalance(balanceRepository.save(new Balance(0.0)));
		account.setStatus(accountStatusRepository.findByName("ACTIVE"));
		return accountRepository.save(account);
	}
}
