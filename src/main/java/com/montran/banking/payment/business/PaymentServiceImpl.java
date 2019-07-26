package com.montran.banking.payment.business;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void verify(Long id, PaymentVerifyDTO paymentVerifyDTO) {
		Payment payment = paymentRepository.findById(id).get();
		if (!payment.getAmount().equals(paymentVerifyDTO.getAmount())) {
			// audit
			return;
		}
		// payment amount and verify amount do match
		payment.setStatus(paymentStatusRepository.findByName("APPROVE"));
		paymentRepository.save(payment);
	}

	@Override
	public void approve(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// check account status
		if (!checkAccountCanDebit(payment.getDebitAccount())) {
			// authorize
			return false;
		}
		if (!checkAccountCanCredit(payment.getCreditAccount())) {
			// authorize
			return false;
		}
		// check account balance
		if (!checkAccountSufficientFunds(payment.getDebitAccount())) {
			// authorize
			return false;
		}
		payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE"));
		paymentRepository.save(payment);
	}

	@Override
	public void authorize(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		payment.setStatus(paymentStatusRepository.findByName("COMPLETED"));
		paymentRepository.save(payment);
	}

	@Override
	public void cancel(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		payment.setStatus(paymentStatusRepository.findByName("CANCELLED"));
		paymentRepository.save(payment);
	}
}
