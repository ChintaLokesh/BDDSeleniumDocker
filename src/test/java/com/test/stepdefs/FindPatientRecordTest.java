package com.test.stepdefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.automaion.pagefactory.Homepage;
import com.automaion.pagefactory.LoginPage;
import com.automaion.pagefactory.VitalsPage;
import com.automation.resources.GenericLibrary;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FindPatientRecordTest extends GenericLibrary {

	
		
		
		public LoginPage loginPageObj;
		public Homepage homePageObj;
		public VitalsPage vitalsPageObj;
		public DesiredCapabilities caps;
		public RemoteWebDriver driver;
		ExtentReports extent;
		 ExtentTest logger;
		Properties prop;
		File filename=null;

		public String expectedpatientName="";
		
		@Given("^User Login To APP with \"([^\"]*)\" username and \"([^\"]*)\" password$")
		public void user_Login_To_APP_with_username_and_password(String username, String password) throws Throwable {
		
			int count=0;
			boolean status =true; 	
			
			loginPageObj.usernameField.sendKeys(username);
			loginPageObj.passwordtext.sendKeys(password);
			filename=takeScreenshot(driver, "LOGINPAGE");
		
			 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
			loginPageObj.inpatientWard.click();
			
		
			while(status) {
			
				driver.findElement(By.xpath("//input[@id='loginButton' and @type='submit']")).submit();
				count++;
			java.util.List<WebElement> list =homePageObj.homePageLabel;
			
			if(list.size()>0 ) {
				status=false;
			}
			if(count==5) {break;}
			}
	        
			filename=takeScreenshot(driver, "HOMEPAGE");
		
			 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
			 logger.pass("user logged in successsfully");
			
		}

		@When("^User Gives the \"([^\"]*)\"Patient Name$")
		public void user_Gives_the_Patient_Name(String patientName) throws Throwable {
			java.util.List<WebElement> list =homePageObj.homePageLabel;
			  expectedpatientName=patientName;
			 homePageObj.findPatientRecordButton.click();
	         homePageObj.patientSearchText.clear();
	         homePageObj.patientSearchText.sendKeys(expectedpatientName);
	         logger.pass("user entered "+ patientName +" as patient name");
	         Thread.sleep(5000);
	        
		}
		
		@Then("^User gets the patient Summary Details of the procedure test for the patient \"([^\"]*)\"$")
		public void findPatientVitalsRecordInLabModule(String patientName) throws Exception {
			
			vitalsPageObj=PageFactory.initElements(driver, VitalsPage.class);
			
		
			Assert.assertEquals(patientName.trim(), driver.findElement(By.xpath("//*[text()='"+patientName+"']")).getText().trim());
			clickByUsingActionsClass(driver,driver.findElement(By.xpath("//*[text()='"+patientName+"']")));
			Thread.sleep(10000);
			
			
			Assert.assertTrue(vitalsPageObj.vitalsSectionLabel.size() >0);
			Assert.assertTrue(vitalsPageObj.vitalsSectionListCount.size() >0);
			
			
			System.out.println("vitals section Attributes count is "+vitalsPageObj.vitalsSectionListCount.size());
			
			System.out.println(" Height value is "+ vitalsPageObj.heightValue.get(0).getText() +" "+ vitalsPageObj.heightMeasurementTypeValue.get(0).getText()); 
			System.out.println(" Weight value is "+ vitalsPageObj.weightValue.get(0).getText() +" "+ vitalsPageObj.weightMeasurementTypeValue.get(0).getText());
			System.out.println(" Temperature value is "+ vitalsPageObj.tempValue.get(0).getText() +" "+ vitalsPageObj.tempMeasurementTypeValue.get(0).getText());
			System.out.println(" Heart Rate  value is "+ vitalsPageObj.heart_rate_Value.get(0).getText() +" "+ vitalsPageObj.heart_rate_TypeValue.get(0).getText());
			System.out.println(" Respiratory_Rate value is "+ vitalsPageObj.respiratory_rate_Value.get(0).getText() +" "+ vitalsPageObj.respiratory_rate_Type_Value.get(0).getText());
			System.out.println(" BP value is "+ vitalsPageObj.bp_systolic_Value.get(0).getText() +" / "+ vitalsPageObj.bp_diastolic_Value.get(0).getText());
			System.out.println(" Oxygen Measurement value is "+ vitalsPageObj.o2_sat_Value.get(0).getText() +" "+ vitalsPageObj.o2_sat_Type_Value.get(0).getText());
			System.out.println(" BMI value is "+ vitalsPageObj.bmi_Value.get(0).getText() );
			
			logger.pass("vitals Details of a patient "+ patientName +": Height value is "+ vitalsPageObj.heightValue.get(0).getText() +" "+ vitalsPageObj.heightMeasurementTypeValue.get(0).getText()); 
			logger.pass("vitals Details of a patient "+ patientName +":Weight value is "+ vitalsPageObj.weightValue.get(0).getText() +" "+ vitalsPageObj.weightMeasurementTypeValue.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName + ":Temperature value is "+ vitalsPageObj.tempValue.get(0).getText() +" "+ vitalsPageObj.tempMeasurementTypeValue.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName + ":Heart Rate  value is "+ vitalsPageObj.heart_rate_Value.get(0).getText() +" "+ vitalsPageObj.heart_rate_TypeValue.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName +":Respiratory_Rate value is "+ vitalsPageObj.respiratory_rate_Value.get(0).getText() +" "+ vitalsPageObj.respiratory_rate_Type_Value.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName +":BP value is "+ vitalsPageObj.bp_systolic_Value.get(0).getText() +" / "+ vitalsPageObj.bp_diastolic_Value.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName + ":Oxygen Measurement value is "+ vitalsPageObj.o2_sat_Value.get(0).getText() +" "+ vitalsPageObj.o2_sat_Type_Value.get(0).getText());
			logger.pass("vitals Details of a patient "+ patientName +":BMI value is "+ vitalsPageObj.bmi_Value.get(0).getText() );
			
			
			filename=takeScreenshot(driver, "Patient_Vitals_In_LabModule_page");
			logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
			 logger.pass("User can see the   patient vital  details with patient name  :"+ patientName);
		
		}
		
		@Before
		public void setupDriver() throws Exception
		{
			
			
			
			String webURL="https://demo.openmrs.org/openmrs/login.htm";
			String today_date=new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
			String className = this.getClass().getSimpleName();
		       
			ExtentHtmlReporter reporter=new ExtentHtmlReporter("./target/"+className+"_"+today_date+".html");
				
			    extent = new ExtentReports();
			    
			    extent.attachReporter(reporter);
			    
			   logger=extent.createTest("Find Patient Record Details");
				
			   String host="localhost";
			   
			   caps=DesiredCapabilities.chrome();
			   
			   if(System.getProperty("HUB_HOST") !=null)
			   {
				   host=System.getProperty("HUB_HOST");
			   }
			   
			   String completeUrl= "http://"+ host + ":4444/wd/hub";
			   driver= new RemoteWebDriver(new URL(completeUrl),caps);
			   

			 
			 	loginPageObj =PageFactory.initElements(driver, LoginPage.class);
			 	homePageObj =PageFactory.initElements(driver, Homepage.class);
			 	
			 	driver.get(webURL);
			 	filename=takeScreenshot(driver, "MainPage");
			 	
			    logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
			    logger.pass("Test Execution started and Browser is opened");
			 		
		}
		
		@After
		public void tearDown() throws Exception
		{
			
			filename=takeScreenshot(driver, "LogoutPage");
	    
	     	 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
	     	 logger.pass("user logged out and  closed the browser");
	  	     extent.flush();
			 driver.quit();
		}

		
	
}
