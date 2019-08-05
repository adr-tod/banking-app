package com.montran.banking.payment.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.montran.banking.account.business.AccountService;
import com.montran.banking.account.domain.dto.AccountCreateDTO;
import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.accountstatus.persistence.AccountStatusRepository;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.payment.persistence.PaymentRepository;
import com.montran.banking.paymentstatus.persistence.PaymentStatusRepository;
import com.montran.banking.user.business.UserService;

@SpringBootTest
@WithMockUser(username = "igibson")
class PaymentServiceImplTest {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	@Autowired
	private AccountStatusRepository accountStatusRepository;

	private Account account1;
	private Account account2;

	@BeforeEach
	public void setUp() {
		// test accounts
		account1 = accountService.create(new AccountCreateDTO("Home", "RO1111111111", "EUR", "Craiova", (long) 1));
		account2 = accountService.create(new AccountCreateDTO("Work", "RO2222222222", "EUR", "Oradea", (long) 2));
	}

	@AfterEach
	public void tearDown() {
		accountService.deleteByIban(account1.getIban());
		accountService.deleteByIban(account2.getIban());
	}

	@Test
	public void testFindAll() {
		paymentService.findAll().forEach(payment -> {
			assertNotNull(payment);
		});
	}

	@Test
	public void testFindAllByAccountId() {
		paymentService.findAllByAccountId((long) 1).forEach(payment -> {
			assertNotNull(payment);
		});
	}

	@Test
	public void testCreate() {
		// create payment (account1 -> account2)
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		assertNotNull(payment);
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testCreateDebitAccountNotFoundShouldThrowException() {
		// create payment (nonExistentAccount -> account2)
		assertThrows(RuntimeException.class,
				() -> paymentService.create(new PaymentCreateDTO("FORTZASTEAUA", account2.getIban(), 1.0, "EUR")));
	}

	@Test
	public void testCreateCreditAccountNotFoundShouldThrowException() {
		// create payment (account1 -> nonExistentAccount)
		assertThrows(RuntimeException.class,
				() -> paymentService.create(new PaymentCreateDTO(account1.getIban(), "FORTZASTEAUA", 1.0, "EUR")));
	}

	@Test
	public void testCreateCurrencyNotFoundShouldThrowException() {
		// create payment (account1 -> account2) with non-existent currency 'ZZZ'
		assertThrows(RuntimeException.class,
				() -> paymentService.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "ZZZ")));
	}

	@Test
	public void testVerifyStatusNotVerifyShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
		// verify payment with status 'APPROVE'
		assertThrows(RuntimeException.class, () -> paymentService.verify(payment.getId(), new PaymentVerifyDTO(1.0)));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testVerifyUserToVerifyAlsoCreateShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		// verify payment by the one who created it
		assertThrows(RuntimeException.class, () -> paymentService.verify(payment.getId(), new PaymentVerifyDTO(1.0)));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testVerifyConfirmAmountNotMatchingShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		paymentRepository.save(payment);
		// verify payment with not matching confirm amount
		assertThrows(RuntimeException.class,
				() -> paymentService.verify(payment.getId(), new PaymentVerifyDTO(9999.0)));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testVerify() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		paymentRepository.save(payment);
		// verify payment with not matching confirm amount
		assertDoesNotThrow(() -> paymentService.verify(payment.getId(), new PaymentVerifyDTO(1.0)));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testApproveStatusNotApproveShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		payment.setVerifiedBy(userService.findByUsername("sean.feeney"));
		payment.setStatus(paymentStatusRepository.findByName("VERIFY"));
		paymentRepository.save(payment);
		// approve payment with status 'VERIFY'
		assertThrows(RuntimeException.class, () -> paymentService.approve(payment.getId()));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testApproveUserToApproveAlsoCreateShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("igibson"));
		payment.setVerifiedBy(userService.findByUsername("sean.feeney"));
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
		// approve payment by the one who created it
		assertThrows(RuntimeException.class, () -> paymentService.approve(payment.getId()));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testApproveUserToApproveAlsoVerifyShouldThrowException() {
		// setup
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		payment.setVerifiedBy(userService.findByUsername("igibson"));
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
		// approve payment by the one who verified it
		assertThrows(RuntimeException.class, () -> paymentService.approve(payment.getId()));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	@Test
	public void testApproveAccountCannotDebitShouldThrowException() {
		// setup
		account1.getBalance().setAvailable(5.0);
//		account1.setStatus(accountStatusRepository.findByName("BLOCKED").get());
		accountRepository.save(account1);
		Payment payment = paymentService
				.create(new PaymentCreateDTO(account1.getIban(), account2.getIban(), 1.0, "EUR"));
		payment.setCreatedBy(userService.findByUsername("skuhn"));
		payment.setVerifiedBy(userService.findByUsername("sean.feeney"));
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
		// approve payment with debit account blocked
		assertThrows(RuntimeException.class, () -> paymentService.approve(payment.getId()));
		// cleanup
		paymentService.deleteById(payment.getId());
	}

	// ACTIVATE CODE ASSIST FOR .
}
