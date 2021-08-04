package com.automation.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.automaion.pagefactory.Homepage;
import com.automaion.pagefactory.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class GenericLibrary {
	
	ExtentReports extent;
	ExtentTest logger;
	Properties prop;
	File filename=null;
	public EdgeDriver driver;
	public LoginPage loginPageObj;
	public Homepage homePageObj;
	
	
	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public  File takeScreenshot(WebDriver driver,String fileName) throws Exception {
		String today_date=new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 	File dst= new File(System.getProperty("user.dir")+"/target/"+fileName+today_date+".jpg");
	 	FileUtils.copyFile(src,dst);
	 	return dst;
	}
	
	public void waitUntilPageIsLoaded(WebDriver driver,int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public void waitUntilElementIsDisplayed(WebDriver driver,By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	
	public void clickByUsingActionsClass(WebDriver driver,WebElement wb) {
		 Actions act = new Actions(driver);
		 act.moveToElement(wb).click().build().perform();
	}
	
	
	
}
