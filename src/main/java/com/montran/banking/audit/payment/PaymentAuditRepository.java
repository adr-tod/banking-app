package com.montran.banking.audit.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAuditRepository extends CrudRepository<PaymentAudit, Long> {

}
