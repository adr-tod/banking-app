package com.montran.banking.payment.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.payment.domain.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

	public Iterable<Payment> findAllByDebitAccountId(Long id);

	public Iterable<Payment> findAllByCreditAccountId(Long id);
}