package com.montran.banking.payment.business;

import com.montran.banking.payment.domain.entity.Payment;

public interface PaymentService {

	public Iterable<Payment> findAll();
}
