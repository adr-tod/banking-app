package com.montran.banking.payment.domain.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.montran.banking.account.persistence.AccountRepository;
import com.montran.banking.currency.persistence.CurrencyRepository;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.paymentstatus.persistence.PaymentStatusRepository;
import com.montran.banking.user.persistence.UserRepository;

@Component
public class PaymentConverter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CurrencyRepository currencyRepostory;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	public Payment convertCreateDtoToEntity(PaymentCreateDTO paymentCreateDTO) {
		Payment payment = new Payment();
		payment.setDebitAccount(
				accountRepository.findByIban(paymentCreateDTO.getDebitIban()).orElseThrow(() -> new RuntimeException(
						String.format("No account with IBAN = '%s'", paymentCreateDTO.getDebitIban()))));
		payment.setCreditAccount(
				accountRepository.findByIban(paymentCreateDTO.getCreditIban()).orElseThrow(() -> new RuntimeException(
						String.format("No account with IBAN = '%s'", paymentCreateDTO.getCreditIban()))));
		payment.setAmount(paymentCreateDTO.getAmount());
		payment.setCurrency(
				currencyRepostory.findByName(paymentCreateDTO.getCurrency()).orElseThrow(() -> new RuntimeException(
						String.format("No currency with name = '%s'", paymentCreateDTO.getCurrency()))));
		payment.setStatus(paymentStatusRepository.findByName("VERIFY").get());
		payment.setDateTime(LocalDateTime.now());
		payment.setCreatedBy(userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
		return payment;
	}
}
