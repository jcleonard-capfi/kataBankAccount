package com.capfi.jcl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountOperation {
	public enum OperationType {
	    DEPOSITE,WITHDRAWAL
	}
	OperationType operation;
	Date date;
	BigDecimal amount;
	BigDecimal balance;
	
	
	public AccountOperation(OperationType operation, Date date, BigDecimal amount, BigDecimal balance) {
		this.operation = operation;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}
	
	public OperationType getOperationType() {
		return operation;
	}
	
	public Date getOperationDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		return "AccountOperation [date=" + sdf.format(date) + ", operation=" + operation + ", amount=" + amount + ", balance="
				+ balance + "]";
	}
	
	
}
