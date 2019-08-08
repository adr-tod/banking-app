package com.montran.banking.payment.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.montran.banking.account.domain.entity.Account;
import com.montran.banking.audit.payment.PaymentAudit;
import com.montran.banking.audit.payment.PaymentAuditRepository;
import com.montran.banking.balance.domain.entity.Balance;
import com.montran.banking.balance.persistence.BalanceRepository;
import com.montran.banking.payment.domain.converter.PaymentConverter;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.payment.persistence.PaymentRepository;
import com.montran.banking.paymentstatus.persistence.PaymentStatusRepository;
import com.montran.banking.user.persistence.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentAuditRepository paymentAuditRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private PaymentConverter paymentConverter;

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
	public Iterable<Payment> findAllByAccountId(Long id) {
		List<Payment> all = new ArrayList<>();
		paymentRepository.findAllByDebitAccountId(id).forEach(payment -> {
			all.add(payment);
		});
		paymentRepository.findAllByCreditAccountId(id).forEach(payment -> {
			all.add(payment);
		});
		return all;
	}

	@Override
	public Payment create(PaymentCreateDTO paymentCreateDTO) {
		Payment payment = paymentConverter.convertCreateDtoToEntity(paymentCreateDTO);
		payment = paymentRepository.save(payment);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("create", SecurityContextHolder.getContext().getAuthentication().getName(),
						"created the payment with id = " + payment.getId()));
		return payment;
	}

	@Override
	public void verify(Long id, PaymentVerifyDTO paymentVerifyDTO) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is VERIFY
		if (!payment.getStatus().getName().equalsIgnoreCase("VERIFY")) {
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("verify", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format("verify failed because the payment (id = %d) status was not 'VERIFY'", id)));
			throw new RuntimeException("Payment status is not 'VERIFY'!");
		}
		// check if not the user that wants to verify the payment also created it
		if (SecurityContextHolder.getContext().getAuthentication().getName()
				.equalsIgnoreCase(payment.getCreatedBy().getUsername())) {
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("verify", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format("verify failed because the payment (id = %d) was also created by him", id)));
			throw new RuntimeException("Payment cannot be verified by the admin who created it!");
		}
		// check if confirm amount matches the payment amount
		if (!payment.getAmount().equals(paymentVerifyDTO.getAmount())) {
			// audit
			paymentAuditRepository.save(new PaymentAudit("verify",
					SecurityContextHolder.getContext().getAuthentication().getName(), String.format(
							"verify failed because the payment (id = %d) amount and confirm amount didn't match", id)));
			throw new RuntimeException("Confirm amount doesn't match the payment amount!");
		}
		// payment amount and verify amount do match
		payment.setStatus(paymentStatusRepository.findByName("APPROVE").get());
		payment.setVerifiedBy(
				userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
		paymentRepository.save(payment);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("verify", SecurityContextHolder.getContext().getAuthentication().getName(),
						String.format("verified the payment with id = %d", id)));
	}

	private Boolean checkAccountCanDebit(Account account) {
		String accountStatus = account.getStatus().getName();
		return !accountStatus.equalsIgnoreCase("CLOSED") && !accountStatus.equalsIgnoreCase("BLOCKED")
				&& !accountStatus.equalsIgnoreCase("BLOCKED_DEBIT");
	}

	private Boolean checkAccountCanCredit(Account account) {
		String accountStatus = account.getStatus().getName();
		return !accountStatus.equalsIgnoreCase("CLOSED") && !accountStatus.equalsIgnoreCase("BLOCKED")
				&& !accountStatus.equalsIgnoreCase("BLOCKED_CREDIT");
	}

	private Boolean checkAccountSufficientFunds(Account account, Payment payment) {
		Double accountAmount = account.getBalance().getAvailable();
		Double paymentAmount = payment.getAmount();
		String accountCurrency = account.getCurrency().getName();
		String paymentCurrency = payment.getCurrency().getName();
		return accountAmount * getExchangeRate(accountCurrency, paymentCurrency) >= paymentAmount;
	}

	private void updateBalances(Payment payment) {
		Balance debitAccountBalance = payment.getDebitAccount().getBalance();
		Balance creditAccountBalance = payment.getCreditAccount().getBalance();
		debitAccountBalance.substractFromAvailable(payment.getAmount()
				* getExchangeRate(payment.getCurrency().getName(), payment.getDebitAccount().getCurrency().getName()));
		creditAccountBalance.addToAvailable(payment.getAmount()
				* getExchangeRate(payment.getCurrency().getName(), payment.getCreditAccount().getCurrency().getName()));
		balanceRepository.save(debitAccountBalance);
		balanceRepository.save(creditAccountBalance);
	}

	@Override
	public void approve(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is APPROVE
		if (!payment.getStatus().getName().equalsIgnoreCase("APPROVE")) {
			// audit
			paymentAuditRepository.save(new PaymentAudit("approve",
					SecurityContextHolder.getContext().getAuthentication().getName(),
					String.format("approve failed because the payment (id = %d) status was not 'APPROVE'", id)));
			throw new RuntimeException("Payment status is not 'APPROVE'!");
		}
		// check if not the user that wants to approve the payment also created or
		// verify it
		if (SecurityContextHolder.getContext().getAuthentication().getName()
				.equalsIgnoreCase(payment.getCreatedBy().getUsername())
				|| SecurityContextHolder.getContext().getAuthentication().getName()
						.equalsIgnoreCase(payment.getVerifiedBy().getUsername())) {
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("approve", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format(
									"approve failed because the payment (id = %d) was also created or verified by him",
									id)));
			throw new RuntimeException("Payment cannot be approved by the admin who created or verified it!");
		}
		// check debit account status
		if (!checkAccountCanDebit(payment.getDebitAccount())) {
			// route to authorize
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE").get());
			payment.setApprovedBy(userRepository
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
			paymentRepository.save(payment);
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("approve", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format("approve failed because the payment (id = %d) debit account status was '%s'",
									id, payment.getDebitAccount().getStatus().getName())));
			throw new RuntimeException(String.format("The debit account status is '%s'!\nPayment routed to 'AUTHORIZE'",
					payment.getDebitAccount().getStatus().getName()));
		}
		// check credit account status
		if (!checkAccountCanCredit(payment.getCreditAccount())) {
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE").get());
			payment.setApprovedBy(userRepository
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
			paymentRepository.save(payment);
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("approve", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format("approve failed because the payment (id = %d) debit account status was '%s'",
									id, payment.getCreditAccount().getStatus().getName())));
			throw new RuntimeException(
					String.format("The credit account status is '%s'!\nPayment routed to 'AUTHORIZE'",
							payment.getCreditAccount().getStatus().getName()));
		}
		// check account balance
		if (!checkAccountSufficientFunds(payment.getDebitAccount(), payment)) {
			payment.setStatus(paymentStatusRepository.findByName("AUTHORIZE").get());
			payment.setApprovedBy(userRepository
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
			paymentRepository.save(payment);
			// audit
			paymentAuditRepository
					.save(new PaymentAudit("approve", SecurityContextHolder.getContext().getAuthentication().getName(),
							String.format("approve failed because the payment (id = %d) funds were insufficient", id)));
			throw new RuntimeException("Insufficient funds on the debit account!\nPayment routed to 'AUTHORIZE'");
		}
		// update balances
		updateBalances(payment);

		payment.setStatus(paymentStatusRepository.findByName("COMPLETED").get());
		payment.setApprovedBy(
				userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
		paymentRepository.save(payment);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("approve", SecurityContextHolder.getContext().getAuthentication().getName(),
						String.format("approved the payment with id = %d", id)));
	}

	@Override
	public void authorize(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is AUTHORIZE
		if (!payment.getStatus().getName().equalsIgnoreCase("AUTHORIZE")) {
			// audit
			paymentAuditRepository.save(new PaymentAudit("authorize",
					SecurityContextHolder.getContext().getAuthentication().getName(),
					String.format("authorize failed because the payment (id = %d) status was not 'AUTHORIZE'", id)));
			throw new RuntimeException("Payment status is not 'AUTHORIZE'!");
		}
		// update balances
		updateBalances(payment);

		payment.setStatus(paymentStatusRepository.findByName("COMPLETED").get());
		payment.setAuthorizedBy(
				userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
		paymentRepository.save(payment);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("authorize", SecurityContextHolder.getContext().getAuthentication().getName(),
						String.format("authorized the payment with id = %d", id)));
	}

	@Override
	public void cancel(Long id) {
		Payment payment = paymentRepository.findById(id).get();
		// make sure payment status is not COMPLETED / CANCELLED
		if (payment.getStatus().getName().equalsIgnoreCase("COMPLETED")
				|| payment.getStatus().getName().equalsIgnoreCase("CANCELLED")) {
			// audit
			paymentAuditRepository.save(new PaymentAudit("cancel",
					SecurityContextHolder.getContext().getAuthentication().getName(),
					String.format(
							"cancel failed because the payment (id = %d) status was either 'COMPLETED' or 'CANCELLED'",
							id)));
			throw new RuntimeException("Payment is already in a final state!");
		}
		payment.setStatus(paymentStatusRepository.findByName("CANCELLED").get());
		payment.setCancelledBy(
				userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
		paymentRepository.save(payment);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("cancel", SecurityContextHolder.getContext().getAuthentication().getName(),
						String.format("cancelled the payment with id = %d", id)));
	}

	@Override
	public void deleteById(Long id) {
		paymentRepository.deleteById(id);
		// audit
		paymentAuditRepository
				.save(new PaymentAudit("cancel", SecurityContextHolder.getContext().getAuthentication().getName(),
						String.format("deleted the payment with id = %d", id)));
	}
}
