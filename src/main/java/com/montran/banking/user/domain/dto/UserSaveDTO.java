package com.montran.banking.user.domain.dto;

public class UserSaveDTO {

	private String fullname;
	private String address;
	private String email;
	private String username;
	private String password;
	
	public UserSaveDTO(String fullname, String address, String email, String username, String password) {
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

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
