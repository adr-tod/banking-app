package com.montran.banking.payment.business;

import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;

public interface PaymentService {

	public Iterable<Payment> findAll();

	public void create(PaymentCreateDTO paymentCreateDTO);

	public Boolean verify(Long id, PaymentVerifyDTO paymentVerifyDTO);

	public Boolean approve(Long id);

	public Boolean authorize(Long id);

	public Boolean cancel(Long id);
}
