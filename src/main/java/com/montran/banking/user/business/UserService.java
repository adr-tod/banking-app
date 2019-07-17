package com.montran.banking.user.business;

import com.montran.banking.user.domain.dto.UserSaveDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;

public interface UserService {

	public Iterable<User> findAll();
	
	public void save(UserSaveDTO userSaveDTO);

	public void update(UserUpdateDTO userUpdateDTO);
	
	public void deleteById(Long id);
}
