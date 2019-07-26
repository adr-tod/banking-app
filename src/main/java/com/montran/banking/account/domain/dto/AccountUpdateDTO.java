package com.montran.banking.account.domain.dto;

public class AccountUpdateDTO {
	private Long id;
	private String name;
	private String address;
	private Double balance;
	private String status;

	public AccountUpdateDTO(Long id, String name, String address, Double balance, String status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Double getBalance() {
		return balance;
	}

	public String getStatus() {
		return status;
	}
}
