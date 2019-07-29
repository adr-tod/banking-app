package com.montran.banking.user.business;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.montran.banking.audit.user.UserAudit;
import com.montran.banking.audit.user.UserAuditRepository;
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
	private UserAuditRepository userAuditRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
		user.setPassword(passwordEncoder.encode(userSaveDTO.getPassword()));
		user.setProfile(profileRepository.findByName("customer"));
		user.setAccounts(new ArrayList<>());
		user = userRepository.save(user);
		userAuditRepository
				.save(new UserAudit("create", SecurityContextHolder.getContext().getAuthentication().getName(),
						"created the user with id = " + user.getId()));
	}

	@Override
	public void update(UserUpdateDTO userUpdateDTO) {
		User user = userRepository.findById(userUpdateDTO.getId()).get();
		user.setFullname(userUpdateDTO.getFullname());
		user.setAddress(userUpdateDTO.getAddress());
		user.setEmail(userUpdateDTO.getEmail());
		user = userRepository.save(user);
		userAuditRepository
				.save(new UserAudit("update", SecurityContextHolder.getContext().getAuthentication().getName(),
						"updated the user with id = " + user.getId()));
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		userAuditRepository.save(new UserAudit("delete",
				SecurityContextHolder.getContext().getAuthentication().getName(), "deleted the user with id = " + id));
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
