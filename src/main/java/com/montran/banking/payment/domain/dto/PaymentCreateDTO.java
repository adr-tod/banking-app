package com.montran.banking.payment.domain.dto;

public class PaymentCreateDTO {

	private String debitIban;
	private String creditIban;
	private Double amount;
	private String currency;

	public PaymentCreateDTO() {
		super();
	}

	public PaymentCreateDTO(String debitIban, String creditIban, Double amount, String currency) {
		super();
		this.debitIban = debitIban;
		this.creditIban = creditIban;
		this.amount = amount;
		this.currency = currency;
	}

	public String getDebitIban() {
		return debitIban;
	}

	public void setDebitIban(String debitIban) {
		this.debitIban = debitIban;
	}

	public String getCreditIban() {
		return creditIban;
	}

	public void setCreditIban(String creditIban) {
		this.creditIban = creditIban;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
