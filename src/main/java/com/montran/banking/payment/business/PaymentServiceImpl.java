package com.montran.banking.payment.business;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.currency.persistence.CurrencyRepository;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
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
}
