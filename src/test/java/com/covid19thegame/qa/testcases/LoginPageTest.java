package com.covid19thegame.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.covid19thegame.qa.apis.Covid19theGameAPIs;
import com.covid19thegame.qa.base.TestBase;

import com.covid19thegame.qa.pages.LoginPage;
import com.covid19thegame.qa.util.TestUtil;

import io.restassured.response.Response;


public class LoginPageTest extends TestBase
{

	//ExtentReports extent = new ExtentReports();
	//ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	LoginPage loginPage;	
	Covid19theGameAPIs covidgameapi;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
		covidgameapi = new Covid19theGameAPIs();
		// directory where output is to be printed		
		//extent.attachReporter(spark);		

	}
	
	@DataProvider
	public Object[][] getCovid19TestData(){
		Object data[][] = TestUtil.getTestData("login");
		return data;
	}
	
	@Test(priority=1,dataProvider="getCovid19TestData",enabled=true)
	public void loginToCovidApp(String UserName, String Password,String NewUser,String Points) throws Exception
	{			    
		
		String filePath="";
		ExtentTest test=extent.createTest("TC_002_UserLogin");
		loginPage.navigateToLoginOption(test);
		boolean flg=loginPage.login(UserName, Password,test);
		if(flg)
		{
			
			test.log(Status.PASS,"Login Successful");
			filePath=TestUtil.takeScreenshotAtEndOfTest();	
			test.addScreenCaptureFromPath(filePath, "Logged In Successfully");
		}
		else
		{
			test.log(Status.FAIL,"Login failed !");	
			filePath=TestUtil.takeScreenshotAtEndOfTest();	
			test.addScreenCaptureFromPath(filePath, "Login failed !");
		}
		
		loginPage.completeChallenge(test);			
				
		
	}
	
	@Test(priority=2,dataProvider="getCovid19TestData",enabled=true)
	public void createUserAndUpdateScore(String UserName, String Password,String NewUser,String Points) throws Exception
	{			    
		
		//String strNewUser="AFName20004";
		String filePath="";
		String strScore=Points;
		String strUIScore="";
		ExtentTest test=extent.createTest("TC_003_CreateUserAndUpdateLeaderBoard");		
		Response rspUpdateUser=covidgameapi.api_UpdateUserScore(NewUser,strScore);
		//loginPage.getScore();
		filePath=TestUtil.takeScreenshotAtEndOfTest();			
		strUIScore=loginPage.getLeaderBoardScore(NewUser,test);
		//test.addScreenCaptureFromPath(filePath," Create new user and update leaderboard");
		if(rspUpdateUser.statusCode()==201)
		{
			test.log(Status.INFO,"Status Code :"+rspUpdateUser.statusCode());
			test.log(Status.INFO,"Response Body :"+rspUpdateUser.body().asString());
			test.log(Status.PASS,"Leaderboard updated for new user");
			filePath=TestUtil.takeScreenshotAtEndOfTest();	
			test.addScreenCaptureFromPath(filePath, "Leaderboard updated for new user");			
			test.log(Status.PASS, "UserScore "+strUIScore+" updated for user "+NewUser+"  using API call");	
		}else
		{
			test.log(Status.INFO,"Status Code :"+rspUpdateUser.statusCode());
			test.log(Status.INFO,"Response Body :"+rspUpdateUser.body().asString());
			test.log(Status.INFO, "UserScore : "+strUIScore+" User : "+NewUser);
			test.log(Status.FAIL,"Failed to update leaderboard");
			filePath=TestUtil.takeScreenshotAtEndOfTest();	
			test.addScreenCaptureFromPath(filePath, "Failed to update leaderboard");
		}
		
			
		Assert.assertEquals(strUIScore, Points,"Score should be updated as per details");
		
		
		//test.addScreenCaptureFromPath(filePath, "Logged In Successfully");
		
		
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		TearDown();
	extent.flush();
			
	}
	
}
