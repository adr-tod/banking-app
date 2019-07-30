package com.montran.banking.accountstatus.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.accountstatus.domain.entity.AccountStatus;

@Repository
public interface AccountStatusRepository extends CrudRepository<AccountStatus, Long> {

	public Optional<AccountStatus> findByName(String name);
}
