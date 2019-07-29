package com.montran.banking.audit.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAuditRepository extends CrudRepository<AccountAudit, Long> {

}
