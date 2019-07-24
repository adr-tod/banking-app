package com.montran.banking.user.business;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.montran.banking.profile.persistence.ProfileRepository;
import com.montran.banking.user.domain.dto.UserSaveDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;
import com.montran.banking.user.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Iterable<User> findAll() {
//		System.out.println("TOT SCHIMB");
		Iterable<User> all = userRepository.findAll();
//		for (User user : all) {
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//			userRepository.save(user);
//		}
		return all;
	}

	@Override
	public void save(UserSaveDTO userSaveDTO) {
		User user = new User();
		user.setFullname(userSaveDTO.getFullname());
		user.setAddress(userSaveDTO.getAddress());
		user.setEmail(userSaveDTO.getEmail());
		user.setUsername(userSaveDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userSaveDTO.getPassword()));
		user.setProfile(profileRepository.findByName("customer"));
		user.setAccount(null);
		userRepository.save(user);
	}

	@Override
	public void update(UserUpdateDTO userUpdateDTO) {
		User userToUpdate = userRepository.findById(userUpdateDTO.getId()).get();
		userToUpdate.setFullname(userUpdateDTO.getFullname());
		userToUpdate.setAddress(userUpdateDTO.getAddress());
		userToUpdate.setEmail(userUpdateDTO.getEmail());
		userRepository.save(userToUpdate);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
