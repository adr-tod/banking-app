package com.montran.banking.user.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.montran.banking.user.domain.dto.UserCreateDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;
import com.montran.banking.user.persistence.UserRepository;
import com.montran.banking.userprofile.persistence.UserProfileRepository;

@Component
public class UserConverter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User convertCreateDtoToEntity(UserCreateDTO userCreateDTO) {
		User user = new User();
		user.setFullname(userCreateDTO.getFullname());
		user.setAddress(userCreateDTO.getAddress());
		user.setEmail(userCreateDTO.getEmail());
		user.setUsername(userCreateDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
		user.setProfile(userProfileRepository.findByName("customer").orElse(null));
		return user;
	}

	public User convertUpdateDtoToEntity(UserUpdateDTO userUpdateDTO) {
		User user = userRepository.findById(userUpdateDTO.getId()).orElse(null);
		user.setFullname(userUpdateDTO.getFullname());
		user.setAddress(userUpdateDTO.getAddress());
		user.setEmail(userUpdateDTO.getEmail());
		return user;
	}
}
