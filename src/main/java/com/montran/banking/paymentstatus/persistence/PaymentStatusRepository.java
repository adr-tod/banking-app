package com.montran.banking.paymentstatus.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.paymentstatus.domain.entity.PaymentStatus;

@Repository
public interface PaymentStatusRepository extends CrudRepository<PaymentStatus, Long> {

}
