package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
		features="src/test/resources/features/FindPatientDetails.feature",
		glue= {"com.test.stepdefs"})
public class TestRunner extends AbstractTestNGCucumberTests{

}
