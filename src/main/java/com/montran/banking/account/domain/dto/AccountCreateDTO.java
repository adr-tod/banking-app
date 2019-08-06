package com.montran.banking.account.domain.dto;

public class AccountCreateDTO {

	private String name;
	private String iban;
	private String currency;
	private String address;
	private Long userId;

	public AccountCreateDTO() {
		super();
	}

	public AccountCreateDTO(String name, String iban, String currency, String address, Long userId) {
		super();
		this.name = name;
		this.iban = iban;
		this.currency = currency;
		this.address = address;
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public String getIban() {
		return iban;
	}

	public String getCurrency() {
		return currency;
	}

	public String getAddress() {
		return address;
	}

	public Long getUserId() {
		return userId;
	}
}
