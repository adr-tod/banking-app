package com.montran.banking.user.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;
import com.montran.banking.user.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void update(UserUpdateDTO userUpdateDTO) {
		User userToUpdate = userRepository.findById(userUpdateDTO.getId()).get();
		userToUpdate.setFullname(userUpdateDTO.getFullname());
		userToUpdate.setAddress(userUpdateDTO.getAddress());
		userToUpdate.setEmail(userUpdateDTO.getEmail());
		userRepository.save(userToUpdate);
	}
}
