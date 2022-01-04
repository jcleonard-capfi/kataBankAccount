package com.capfi.jcl;

import java.util.ArrayList;
import java.util.List;

public class AccountOperationHistoryImpl implements AccountOperationHistory{	
	List<AccountOperation> accountOperationList = new ArrayList<AccountOperation>();

	@Override
	public void addOperation(AccountOperation accountOperation) {
		accountOperationList.add(accountOperation);
	}

	@Override
	public List<AccountOperation> getAccountOperationList() {
		return accountOperationList;
	}
	
	
}
