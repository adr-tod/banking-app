package com.montran.banking.payment.domain.dto;

public class PaymentCreateDTO {

	private String debitAccountIban;
	private String creditAccountIban;
	private Double amount;
	private String currency;

	public String getDebitAccountIban() {
		return debitAccountIban;
	}

	public void setDebitAccountIban(String debitAccountIban) {
		this.debitAccountIban = debitAccountIban;
	}

	public String getCreditAccountIban() {
		return creditAccountIban;
	}

	public void setCreditAccountIban(String creditAccountIban) {
		this.creditAccountIban = creditAccountIban;
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
