package com.montran.banking.user.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.montran.banking.audit.user.UserAudit;
import com.montran.banking.audit.user.UserAuditRepository;
import com.montran.banking.user.domain.converter.UserConverter;
import com.montran.banking.user.domain.dto.UserCreateDTO;
import com.montran.banking.user.domain.dto.UserUpdateDTO;
import com.montran.banking.user.domain.entity.User;
import com.montran.banking.user.persistence.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuditRepository userAuditRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("No user with id = '%d'", id)));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException(String.format("No user with username = '%s'", username)));
	}

	@Override
	public User create(UserCreateDTO userCreateDTO) {
		User user = userConverter.convertCreateDtoToEntity(userCreateDTO);
		user = userRepository.save(user);
		// audit
		userAuditRepository
				.save(new UserAudit("create", SecurityContextHolder.getContext().getAuthentication().getName(),
						"created the user with id = " + user.getId()));
		return user;
	}

	@Override
	public User update(UserUpdateDTO userUpdateDTO) {
		User user = userConverter.convertUpdateDtoToEntity(userUpdateDTO);
		user = userRepository.save(user);
		// audit
		userAuditRepository
				.save(new UserAudit("update", SecurityContextHolder.getContext().getAuthentication().getName(),
						"updated the user with id = " + user.getId()));
		return user;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		// audit
		userAuditRepository.save(new UserAudit("delete",
				SecurityContextHolder.getContext().getAuthentication().getName(), "deleted the user with id = " + id));
	}

	@Override
	public void deleteByUsername(String username) {
		userRepository.deleteByUsername(username);
		// audit
		userAuditRepository
				.save(new UserAudit("delete", SecurityContextHolder.getContext().getAuthentication().getName(),
						"deleted the user with username = " + username));
	}
}
