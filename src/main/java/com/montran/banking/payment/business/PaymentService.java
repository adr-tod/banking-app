package com.montran.banking.payment.business;

import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;

public interface PaymentService {

	public Iterable<Payment> findAll();

	public Iterable<Payment> findAllByAccountId(Long id);

	public Payment create(PaymentCreateDTO paymentCreateDTO);

	public void verify(Long id, PaymentVerifyDTO paymentVerifyDTO);

	public void approve(Long id);

	public void authorize(Long id);

	public void cancel(Long id);
}
