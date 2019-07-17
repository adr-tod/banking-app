package com.montran.banking.payment.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.payment.domain.entity.Payment;
import com.montran.banking.payment.persistence.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Iterable<Payment> findAll() {
		return paymentRepository.findAll();
	}
}
