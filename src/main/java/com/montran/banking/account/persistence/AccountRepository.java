package com.montran.banking.account.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.account.domain.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	public Optional<Account> findByIban(String iban);
	
	public Iterable<Account> findAllByUserId(Long id);
}
