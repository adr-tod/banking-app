package com.montran.banking.user.domain.dto;

public class UserUpdateDTO {
	private Long id;
	private String fullname;
	private String address;
	private String email;

	public UserUpdateDTO() {
		super();
	}

	public UserUpdateDTO(Long id, String fullname, String address, String email) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
	}

	public Long getId() {
		return id;
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
}
