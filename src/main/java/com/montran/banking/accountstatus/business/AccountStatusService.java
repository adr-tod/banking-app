package com.montran.banking.accountstatus.business;

import com.montran.banking.accountstatus.domain.entity.AccountStatus;

public interface AccountStatusService {
	
	public Iterable<AccountStatus> findAll();
}
