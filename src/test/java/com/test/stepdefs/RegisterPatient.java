package com.test.stepdefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automaion.pagefactory.Homepage;
import com.automaion.pagefactory.LoginPage;
import com.automation.resources.GenericLibrary;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterPatient extends GenericLibrary{
	
	public LoginPage loginPageObj;
	public Homepage homePageObj;
	public DesiredCapabilities caps;
	public Properties prop;
	public ExtentReports extent;
	public ExtentTest logger;
	File filename = null;
//	public RemoteWebDriver driver;
	public String expectedpatientName;
	public EdgeDriver driver;
	
	


	@Then("^Patient Should be Registered with the given Patient Name$")
	public void patient_Should_be_Registered_with_the_given_Patient_Name() throws Throwable {
		
		homePageObj=PageFactory.initElements(driver, Homepage.class);
		
		 if(homePageObj.patientRecordsResults.size()>0) {
	       	  System.out.println("patient Name is "+homePageObj.patientName.getText());
	       	  Assert.assertEquals(homePageObj.patientName.getText(),expectedpatientName);
	       	  filename=takeScreenshot(driver, "PatientNameDisplayInUI");
	       	
	       
	       	  logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
	       	  logger.pass("user able to find the patient name :  "+ expectedpatientName +" successfully");
	         }
	}

	


}
