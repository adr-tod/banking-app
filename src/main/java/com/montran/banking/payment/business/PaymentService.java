package com.montran.banking.payment.business;

import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.entity.Payment;

public interface PaymentService {

	public Iterable<Payment> findAll();
	
	public void create(PaymentCreateDTO paymentCreateDTO);
	
	public void verify(Long id);

	public void approve(Long id);
	
	public void authorize(Long id);
	
	public void cancel(Long id);
}
