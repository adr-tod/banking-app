package com.montran.banking.audit.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuditRepository extends CrudRepository<UserAudit, Long> {

}
