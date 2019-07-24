package com.montran.banking.security;

import java.io.Serializable;

import com.montran.banking.user.domain.entity.User;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final Long id;
	private final String username;
	private final String profile;
	private final String jwttoken;

	public JwtResponse(User user, String jwttoken) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.profile = user.getProfile().getName();
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getProfile() {
		return profile;
	}

}