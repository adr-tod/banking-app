package com.montran.banking.accountstatus.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.accountstatus.domain.entity.AccountStatus;
import com.montran.banking.accountstatus.persistence.AccountStatusRepository;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

	@Autowired
	private AccountStatusRepository accountStatusRepository;
	
	@Override
	public Iterable<AccountStatus> findAll() {
		return accountStatusRepository.findAll();
	}
}
