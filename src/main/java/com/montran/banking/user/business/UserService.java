package com.montran.banking.user.business;

import com.montran.banking.user.domain.entity.User;

public interface UserService {
	
	public Iterable<User> findAll();
}
