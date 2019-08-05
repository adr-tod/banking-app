package com.montran.banking.account.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.dto.AccountUpdateDTO;
import com.montran.banking.account.domain.entity.Account;

@SpringBootTest
@WithMockUser(username = "someUsername")
class AccountServiceImplTest {

	@Autowired
	private AccountService accountService;

	@BeforeEach
	public void setUp() {
		accountService.create(new AccountCreateDTO("Home", "RO8888888888", "USD", "Cluj-Napoca", (long) 1));
	}

	@AfterEach
	public void tearDown() {
		accountService.deleteByIban("RO8888888888");
	}

	@Test
	public void testFindAll() {
		accountService.findAll().forEach(account -> {
			assertNotNull(account);
		});
	}

	@Test
	public void testFindAllByUserId() {
		accountService.findAllByUserId((long) 3).forEach(account -> {
			assertNotNull(account);
		});
	}

	@Test
	public void testCreate() {
		// no 'RO1234567890' before create
		assertThrows(RuntimeException.class, () -> accountService.findByIban("RO1234567890"));
		// create 'RO1234567890'
		assertNotNull(
				accountService.create(new AccountCreateDTO("Expenses", "RO1234567890", "EUR", "Bucharest", (long) 2)));
		// a 'RO1234567890' after create
		assertNotNull(accountService.findByIban("RO1234567890"));
		// cleanup
		accountService.deleteByIban("RO1234567890");
	}

	@Test
	public void testCreateCurrencyNotFoundShouldThrowException() {
		// create account with non-existent currency 'ZZZ'
		assertThrows(RuntimeException.class, () -> accountService
				.create(new AccountCreateDTO("Expenses", "RO1234567890", "ZZZ", "Bucharest", (long) 2)));
	}

	@Test
	public void testCreateUserNotFoundShouldThrowException() {
		// create account with non-existent user with id = 9999
		assertThrows(RuntimeException.class, () -> accountService
				.create(new AccountCreateDTO("Expenses", "RO1234567890", "EUR", "Bucharest", (long) 9999)));
	}

	@Test
	public void testUpdate() {
		Account account = accountService.findByIban("RO8888888888");
		// check address is 'Cluj-Napoca'
		assertEquals("Cluj-Napoca", account.getAddress());
		// update address to 'Cluj-Napoca UPDATED'
		assertNotNull(accountService
				.update(new AccountUpdateDTO(account.getId(), account.getName(), account.getAddress() + " UPDATED",
						account.getBalance().getAvailable(), account.getStatus().getName())));
		// check address is 'Cluj-Napoca UPDATED'
		assertEquals("Cluj-Napoca UPDATED", accountService.findByIban("RO8888888888").getAddress());
		// cleanup
		accountService.update(new AccountUpdateDTO(account.getId(), account.getName(), "Cluj-Napoca",
				account.getBalance().getAvailable(), account.getStatus().getName()));
	}

	@Test
	public void testUpdateNotFoundStatusShouldThrowException() {
		Account account = accountService.findByIban("RO8888888888");
		// update account with non-existent status 'HELLO'
		assertThrows(RuntimeException.class, () -> accountService.update(new AccountUpdateDTO(account.getId(),
				account.getName(), account.getAddress(), account.getBalance().getAvailable(), "HELLO")));
	}

	@Test
	public void testDeleteById() {
		Long id = accountService.findByIban("RO8888888888").getId();
		// check account with id = x exists
		assertNotNull(accountService.findById(id));
		// delete account with id = x
		accountService.deleteById(id);
		// check account with id = x does not exist
		assertThrows(RuntimeException.class, () -> accountService.findById(id));
		// cleanup
		setUp();
	}
}
