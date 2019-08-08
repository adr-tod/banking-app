package com.montran.banking.account.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.dto.AccountUpdateDTO;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.accountstatus.persistence.AccountStatusRepository;
import com.montran.banking.balance.domain.entity.Balance;
import com.montran.banking.balance.persistence.BalanceRepository;
import com.montran.banking.currency.persistence.CurrencyRepository;
import com.montran.banking.user.persistence.UserRepository;

@Component
public class AccountConverter {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountStatusRepository accountStatusRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	public Account convertCreateDtoToEntity(AccountCreateDTO accountCreateDTO) {
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
		account.setStatus(accountStatusRepository.findByName("ACTIVE").orElse(null));
		return account;
	}

	public Account convertUpdateDtoToEntity(AccountUpdateDTO accountUpdateDTO) {
		Account account = accountRepository.findById(accountUpdateDTO.getId()).orElse(null);
		account.setName(accountUpdateDTO.getName());
		account.setAddress(accountUpdateDTO.getAddress());
		account.getBalance().setAvailable(accountUpdateDTO.getBalance());
		account.setStatus(
				accountStatusRepository.findByName(accountUpdateDTO.getStatus()).orElseThrow(() -> new RuntimeException(
						String.format("No account status with name = '%s'", accountUpdateDTO.getStatus()))));
		return account;
	}
}
