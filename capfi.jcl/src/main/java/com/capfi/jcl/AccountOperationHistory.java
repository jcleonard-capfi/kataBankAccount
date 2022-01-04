package com.capfi.jcl;

import java.util.List;

public interface AccountOperationHistory {	
	void addOperation(AccountOperation accountOperation);
	List<AccountOperation> getAccountOperationList() ;
}
