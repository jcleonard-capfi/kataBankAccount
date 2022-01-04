package com.capfi.jcl.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/capfi/jcl/AppFeatures/BankAccount.feature" , glue = {"com.capfi.jcl.stepdefinitions"})
public class BankAccountTestRunner {

}
