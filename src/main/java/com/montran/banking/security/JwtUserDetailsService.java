package com.montran.banking.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.montran.banking.user.persistence.UserRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.montran.banking.user.domain.entity.User user = userRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//		return new User("javainuse", "$2b$10$a5xhiWff/y5ial28LYVvZeT5n1J0e7nxcyDaWb/wyq9vtBQ71NMZm", new ArrayList<>());
	}

}
