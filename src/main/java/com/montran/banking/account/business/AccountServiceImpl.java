package com.montran.banking.account.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.montran.banking.account.domain.converter.AccountConverter;
import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.dto.AccountUpdateDTO;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.audit.account.AccountAudit;
import com.montran.banking.audit.account.AccountAuditRepository;
import com.montran.banking.balance.persistence.BalanceRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private AccountAuditRepository accountAuditRepository;

	@Autowired
	private AccountConverter accountConverter;

	@Override
	public Account findById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("No account with id = '%d'", id)));
	}

	@Override
	public Account findByIban(String iban) {
		return accountRepository.findByIban(iban)
				.orElseThrow(() -> new RuntimeException(String.format("No account with iban = '%s'", iban)));
	}

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
		Account account = accountConverter.convertCreateDtoToEntity(accountCreateDTO);
		account = accountRepository.save(account);
		// audit
		accountAuditRepository
				.save(new AccountAudit("create", SecurityContextHolder.getContext().getAuthentication().getName(),
						"created the account with id = " + account.getId()));
		return account;
	}

	@Override
	public Account update(AccountUpdateDTO accountUpdateDTO) {
		Account account = accountConverter.convertUpdateDtoToEntity(accountUpdateDTO);
		account = accountRepository.save(account);
		// audit
		accountAuditRepository
				.save(new AccountAudit("update", SecurityContextHolder.getContext().getAuthentication().getName(),
						"updated the account with id = " + account.getId()));
		return account;
	}

	@Override
	public void deleteById(Long id) {
		Account account = accountRepository.findById(id).orElse(null);
		balanceRepository.delete(account.getBalance());
		accountRepository.delete(account);
		// audit
		accountAuditRepository
				.save(new AccountAudit("delete", SecurityContextHolder.getContext().getAuthentication().getName(),
						"deleted the account with id = " + id));
	}

	@Override
	public void deleteByIban(String iban) {
		Account account = accountRepository.findByIban(iban).orElse(null);
		balanceRepository.delete(account.getBalance());
		accountRepository.delete(account);
		// audit
		accountAuditRepository
				.save(new AccountAudit("delete", SecurityContextHolder.getContext().getAuthentication().getName(),
						"deleted the account with id = " + account.getId()));
	}
}
