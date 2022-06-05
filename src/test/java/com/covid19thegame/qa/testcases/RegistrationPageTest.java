package com.covid19thegame.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.covid19thegame.qa.base.TestBase;
import com.covid19thegame.qa.pages.LoginPage;
import com.covid19thegame.qa.pages.RegistrationPage;
import com.covid19thegame.qa.util.TestUtil;

public class RegistrationPageTest extends TestBase
{

	RegistrationPage registrationPage;

	
	public RegistrationPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		registrationPage = new RegistrationPage();	
		//extent.attachReporter(spark);		
		
	}
	
	@DataProvider
	public Object[][] getCovid19TestData(){
		Object data[][] = TestUtil.getTestData("registration");
		return data;
	}
	
	@Test(priority=1,dataProvider="getCovid19TestData")
	public void loginToCovidApp(String UserName, String Password) throws Exception 
	{			    
		
		String filePath="";
		boolean flg=true;
		ExtentTest test=extent.createTest("TC_001_UserRegistration");
		flg=registrationPage.registerUser(UserName,Password,test);	
		if(flg)
		{
			
			test.log(Status.PASS,"Registered Successfully");
		}
		else
		{
			test.log(Status.FAIL,"User registration failed !");	
		}
		
		filePath=TestUtil.takeScreenshotAtEndOfTest();	
		test.addScreenCaptureFromPath(filePath, "User Registration");
		Assert.assertTrue(registrationPage.verifyLoginPage(),"User should land on login page");
		
	}
	
	@AfterMethod
	public void tearDown(){
		TearDown();
        extent.flush();
			
	}
	
	
}
