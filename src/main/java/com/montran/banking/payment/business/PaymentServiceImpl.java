package com.montran.banking.payment.business;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.currency.persistence.CurrencyRepository;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.payment.persistence.PaymentRepository;
import com.montran.banking.paymentstatus.persistence.PaymentStatusRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CurrencyRepository currencyRepostory;

	private final Map<String, Double> exchangeRate = new HashMap<String, Double>() {
		{
			// USD, EUR, JPY, GBP, MXN
			put("USD-USD", 1.0000);
			put("USD-EUR", 0.8959);
			put("USD-JPY", 108.4614);
			put("USD-GBP", 0.8089);
			put("USD-MXN", 19.0446);
			put("EUR-USD", 1.1142);
			put("EUR-EUR", 1.0000);
			put("EUR-JPY", 120.7132);
			put("EUR-GBP", 0.8912);
			put("EUR-MXN", 21.1907);
			put("JPY-USD", 0.0092);
			put("JPY-EUR", 0.0082);
			put("JPY-JPY", 1.0000);
			put("JPY-GBP", 0.0074);
			put("JPY-MXN", 0.1755);
			put("GBP-USD", 1.2381);
			put("GBP-EUR", 1.1112);
			put("GBP-JPY", 134.1131);
			put("GBP-GBP", 1.0000);
			put("GBP-MXN", 23.5630);
			put("MXN-USD", 0.0525);
			put("MXN-EUR", 0.04717);
			put("MXN-JPY", 5.6910);
			put("MXN-GBP", 0.0424);
			put("MXN-MXN", 1.0000);
		}
	};

	private Double getExchangeRate(String from, String to) {
		return exchangeRate.get(from + "-" + to);
	}

	@Override
	public Iterable<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public void create(PaymentCreateDTO paymentCreateDTO) {
		Payment payment = new Payment();
		payment.setDebitAccount(accountRepository.findByIban(paymentCreateDTO.getDebitIban()));
		payment.setCreditAccount(accountRepository.findByIban(paymentCreateDTO.getCreditIban()));
		payment.setAmount(paymentCreateDTO.getAmount());
		payment.setCurrency(currencyRepostory.findByName(paymentCreateDTO.getCurrency()));
		payment.setStatus(paymentStatusRepository.findByName("VERIFY"));
		payment.setDateTime(LocalDateTime.now());
		paymentRepository.save(payment);
	}

	@Override
	public Boolean verify(Long id, PaymentVerifyDTO paymentVerifyDTO) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is VERIFY
		if (!payment.getStatus().getName().equalsIgnoreCase("VERIFY")) {
			return false;
		}
		if (!payment.getAmount().equals(paymentVerifyDTO.getAmount())) {
			// audit
			return false;
		}
		// payment amount and verify amount do match
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
		return true;
	}

	private Boolean checkAccountCanDebit(Account account) {
		String accountStatus = account.getStatus().getName();
		return !accountStatus.equalsIgnoreCase("BLOCKED") && !accountStatus.equalsIgnoreCase("BLOCKED_DEBIT");
	}

	private Boolean checkAccountCanCredit(Account account) {
		String accountStatus = account.getStatus().getName();
		return !accountStatus.equalsIgnoreCase("BLOCKED") && !accountStatus.equalsIgnoreCase("BLOCKED_CREDIT");
	}

	private Boolean checkAccountSufficientFunds(Account account, Payment payment) {
		Double accountAmount = account.getBalance().getAvailable();
		Double paymentAmount = payment.getAmount();
		String accountCurrency = account.getCurrency().getName();
		String paymentCurrency = payment.getCurrency().getName();
		return accountAmount * getExchangeRate(accountCurrency, paymentCurrency) >= paymentAmount;
	}

	@Override
	public Boolean approve(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is APPROVE
		if (!payment.getStatus().getName().equalsIgnoreCase("APPROVE")) {
			return false;
		}
		// check account status
		if (!checkAccountCanDebit(payment.getDebitAccount())) {
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE"));
			paymentRepository.save(payment);
			return false;
		}
		if (!checkAccountCanCredit(payment.getCreditAccount())) {
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE"));
			paymentRepository.save(payment);
			return false;
		}
		// check account balance
		if (!checkAccountSufficientFunds(payment.getDebitAccount(), payment)) {
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE"));
			paymentRepository.save(payment);
			return false;
		}
		// update balances
		Account debitAccount = payment.getDebitAccount();
		Account creditAccount = payment.getCreditAccount();
		debitAccount.getBalance().substractFromAvailable(payment.getAmount()
				* getExchangeRate(payment.getCurrency().getName(), debitAccount.getCurrency().getName()));
		creditAccount.getBalance().addToAvailable(payment.getAmount()
				* getExchangeRate(payment.getCurrency().getName(), creditAccount.getCurrency().getName()));
		accountRepository.save(debitAccount);
		accountRepository.save(creditAccount);

		payment.setStatus(paymentStatusRepository.findByName("COMPLETED"));
		paymentRepository.save(payment);
		return true;
	}

	@Override
	public Boolean authorize(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is AUTHORIZE
		if (!payment.getStatus().getName().equalsIgnoreCase("AUTHORIZE")) {
			return false;
		}
		payment.setStatus(paymentStatusRepository.findByName("COMPLETED"));
		paymentRepository.save(payment);
		return true;
	}

	@Override
	public Boolean cancel(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is not COMPLETED
		if (payment.getStatus().getName().equalsIgnoreCase("COMPLETED")) {
			return false;
		}
		payment.setStatus(paymentStatusRepository.findByName("CANCELLED"));
		paymentRepository.save(payment);
		return true;
	}
}
