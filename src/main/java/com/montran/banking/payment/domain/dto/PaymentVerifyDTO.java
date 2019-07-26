package com.montran.banking.payment.domain.dto;

public class PaymentVerifyDTO {

	private Double amount;

	public PaymentVerifyDTO(Double amount) {
		super();
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}
}
