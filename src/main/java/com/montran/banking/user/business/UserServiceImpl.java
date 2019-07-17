package com.montran.banking.user.business;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void save(UserSaveDTO userSaveDTO) {
		User user = new User();
		user.setFullname(userSaveDTO.getFullname());
		user.setAddress(userSaveDTO.getAddress());
		user.setEmail(userSaveDTO.getEmail());
		user.setUsername(userSaveDTO.getUsername());
		user.setPassword(userSaveDTO.getPassword());
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

}
