package com.montran.banking.user.domain.dto;

public class UserCreateDTO {

	private String fullname;
	private String address;
	private String email;
	private String username;
	private String password;

	public UserCreateDTO() {
		super();
	}

	public UserCreateDTO(String fullname, String address, String email, String username, String password) {
		super();
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
