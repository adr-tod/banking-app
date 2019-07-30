package com.montran.banking.account.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.dto.AccountUpdateDTO;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.accountstatus.persistence.AccountStatusRepository;
import com.montran.banking.audit.account.AccountAudit;
import com.montran.banking.audit.account.AccountAuditRepository;
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

	@Autowired
	private AccountAuditRepository accountAuditRepository;

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
		account.setCurrency(
				currencyRepository.findByName(accountCreateDTO.getCurrency()).orElseThrow(() -> new RuntimeException(
						String.format("No currency with name = '%s'", accountCreateDTO.getCurrency()))));
		account.setAddress(accountCreateDTO.getAddress());
		account.setUser(userRepository.findById(accountCreateDTO.getUserId()).orElseThrow(
				() -> new RuntimeException(String.format("No user with id = '%d'", accountCreateDTO.getUserId()))));
		account.setBalance(balanceRepository.save(new Balance(0.0)));
		account.setStatus(accountStatusRepository.findByName("ACTIVE").get());
		account = accountRepository.save(account);
		accountAuditRepository
				.save(new AccountAudit("create", SecurityContextHolder.getContext().getAuthentication().getName(),
						"created the account with id = " + account.getId()));
		return account;
	}

	@Override
	public Account update(AccountUpdateDTO accountUpdateDTO) {
		Account account = accountRepository.findById(accountUpdateDTO.getId()).get();
		account.setName(accountUpdateDTO.getName());
		account.setAddress(accountUpdateDTO.getAddress());
		account.getBalance().setAvailable(accountUpdateDTO.getBalance());
		account.setStatus(
				accountStatusRepository.findByName(accountUpdateDTO.getStatus()).orElseThrow(() -> new RuntimeException(
						String.format("No account status with name = '%s'", accountUpdateDTO.getStatus()))));
		account = accountRepository.save(account);
		accountAuditRepository
				.save(new AccountAudit("update", SecurityContextHolder.getContext().getAuthentication().getName(),
						"updated the account with id = " + account.getId()));
		return account;
	}

	@Override
	public void delete(Long id) {
		Account account = accountRepository.findById(id).get();
		balanceRepository.delete(account.getBalance());
		accountRepository.delete(account);
		accountAuditRepository
				.save(new AccountAudit("delete", SecurityContextHolder.getContext().getAuthentication().getName(),
						"deleted the account with id = " + id));
	}
}
