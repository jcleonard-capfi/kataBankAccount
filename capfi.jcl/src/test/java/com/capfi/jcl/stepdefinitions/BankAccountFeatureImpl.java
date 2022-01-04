package com.capfi.jcl.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;

import com.capfi.jcl.AccountOperation;
import com.capfi.jcl.BankAccount;
import com.capfi.jcl.FunctionalException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BankAccountFeatureImpl {

	BankAccount ba = null;
	String errorMsg = null;
	String status = "";
	private List<AccountOperation> history;
	
	@Given("As a bank new client")
	public void as_a_bank_new_client() {
		System.out.println("Creating new Account");
		this.ba = new BankAccount();
	}
	
	@When("I want to make a deposit in my new account of amount {bigdecimal}")
	public void i_want_to_make_a_deposit_in_my_new_account_of_amount(BigDecimal bigdecimal) {
		try {
			System.out.println("Setting Deposit of "+bigdecimal);
			this.ba.setDeposit(bigdecimal);
			status = "OK";	
		} catch (FunctionalException e) {
			errorMsg = e.getMessage();
			status = "KO";			
		}
	}
	
	@Then("I verify the account balance is {bigdecimal}")
	public void i_verify_the_account_balance_is(BigDecimal bigdecimal) {
		assertEquals(bigdecimal, ba.getBalance());
	}
	
	@When("I {string} for the {double} on my account")
	public void i_deposit_for_the_on_my_account(String action, Double d) throws FunctionalException {
		
	    if (action.equalsIgnoreCase("DEPOSIT")) {
	    	ba.setDeposit(BigDecimal.valueOf(d));
	    } else if (action.equalsIgnoreCase("DEPOSIT")) {
	    	ba.setWithdrawal(BigDecimal.valueOf(d));
	    } 
	    status = "OK";	
	}
	
	@When("I deposit for the {double} on my account")
	public void i_deposit_for_the_on_my_account(Double d) throws FunctionalException {
	    	ba.setDeposit(BigDecimal.valueOf(d));
	    	status = "OK";	
	}
	
	@When("I withdrawal for the {double} on my account")
	public void i_withdrawal_for_the_on_my_account(Double d) throws FunctionalException {
			try {
				ba.setWithdrawal(BigDecimal.valueOf(d));
			} catch(FunctionalException e) {
				errorMsg = e.getMessage();
				System.out.println("status KO while withdrawal: "+d);
				status = "KO";		
			}
	}

	@Then("I verify the account balance is {double} and OK")
	public void i_verify_the_account_balance_is_and_ok(Double d) {
	    assertEquals(BigDecimal.valueOf(d), ba.getBalance());
	    assertEquals("OK", this.status );
	}

	@Then("I verify the account balance is {double} and KO")
	public void i_verify_the_account_balance_is_and_ko(double d) {
		assertEquals(BigDecimal.valueOf(d).doubleValue(), ba.getBalance().doubleValue(),0.0001);
	    assertEquals("KO", this.status );
	}
	
	@When("I want to retrieve the history")
	public void i_want_to_retrieve_the_history() {
	    this.history = ba.getAccountOperationList();
	    for( AccountOperation ao  :  history ) {
	    	System.out.println("Retreive History: " + ao);
	    }
	}
	
	@Then("I verify that history has {int} lines")
	public void i_verify_that_history_has_lines(Integer int1) {
		assertEquals(int1.intValue(),this.history.size());
	}
	
	@Then("I verify that history last line amount is {double}")
	public void i_verify_that_history_last_line_amount_is(Double double1) {
		assertEquals(double1.doubleValue(), history.get(this.history.size()-1).getAmount().doubleValue(), 0.001);
	}
	
	@Then("I verify that history last line balance is {double}")
	public void i_verify_that_history_last_line_balance_is(Double d) {
		assertEquals(d.doubleValue(), history.get(this.history.size()-1).getBalance().doubleValue(), 0.001);
	}
	
	@Then("I verify that history last line operation is WITHDRAWAL")
	public void i_verify_that_history_last_line_operation_is_withdrawal() {
		assertEquals("WITHDRAWAL", history.get(this.history.size()-1).getOperationType().toString());
	}
	
	@Then("I verify that history last line date is before system {string} date")
	public void i_verify_that_history_last_line_date_is_before_system_date(String string) {
		long delayMs = history.get(this.history.size()-1).getOperationDate().getTime() - System.currentTimeMillis();
		Assert.assertTrue (delayMs <= 0);
	}
	
	
}
