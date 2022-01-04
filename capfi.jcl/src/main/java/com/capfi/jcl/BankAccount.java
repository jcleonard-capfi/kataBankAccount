package com.capfi.jcl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.capfi.jcl.AccountOperation.OperationType;

public class BankAccount {
	BigDecimal balance =  BigDecimal.ZERO;
	AccountOperationHistory history = new AccountOperationHistoryImpl();
	
	public void setDeposit(BigDecimal mnt) throws FunctionalException {
		if( mnt.signum() < 0 )  throw new FunctionalException("Deposit must be positive value");
		balance = balance.add(mnt);
		
		Date operationDate = new Date();
		history.addOperation(new AccountOperation(OperationType.DEPOSITE, operationDate , mnt , balance));
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setWithdrawal(BigDecimal mnt) throws FunctionalException {
		if( mnt.signum() < 0 )  throw new FunctionalException("Withdrawal must be positive value");
		if( balance.subtract(mnt).signum() < 0)  throw new FunctionalException("Withdrawal can not exceed current balance: " + getBalance());
		balance = balance.subtract(mnt);
		
		Date operationDate = new Date();
		history.addOperation(new AccountOperation(OperationType.WITHDRAWAL, operationDate , mnt , balance));
	}

	public List<AccountOperation> getAccountOperationList() {
		return history.getAccountOperationList();
	}


}
