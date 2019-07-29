package com.montran.banking.audit.account;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.montran.banking.base.BaseAudit;

@Entity
@Table(name = "accounts_audit")
public class AccountAudit extends BaseAudit {

	public AccountAudit(String operation, String userBy, String message) {
		this.timestamp = LocalDateTime.now();
		this.operation = operation;
		this.userBy = userBy;
		this.message = message;
	}
}
