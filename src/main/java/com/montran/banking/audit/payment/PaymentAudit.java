package com.montran.banking.audit.payment;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.montran.banking.base.BaseAudit;

@Entity
@Table(name = "payments_audit")
public class PaymentAudit extends BaseAudit {

	public static final String OPERATION_VERIFY = "verify";
	public static final String OPERATION_APPROVE = "approve";
	public static final String OPERATION_AUTHORIZE = "authorize";
	public static final String OPERATION_CANCEL = "cancel";

	public PaymentAudit() {
		super();
	}

	public PaymentAudit(String operation, String userBy, String message) {
		this.timestamp = LocalDateTime.now();
		this.operation = operation;
		this.userBy = userBy;
		this.message = message;
	}
}
