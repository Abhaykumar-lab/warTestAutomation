package com.covid19thegame.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.covid19thegame.qa.base.TestBase;
import com.covid19thegame.qa.util.TestUtil;


public class LoginPage extends TestBase
{
	
	By optionOpenAtOwnRisk = By.xpath("//a[contains(text(),'Enter at your own risk')][@id='news']");
	By loginOption= By.xpath("//a[contains(text(),'Login')]");
	By wusername= By.xpath("//*[@id='worrior_username']");
	By wpwd= By.xpath("//*[@id='worrior_pwd']");
	By loginlnk= By.xpath("//a[contains(text(),'Login warrior')]");
	By startGameBtn= By.xpath("//a[contains(text(),'Start game')][@class='btn']");
	By optionEnterAtYourOwnRisk= By.xpath("//a[contains(text(),'Enter at your own risk')][@id='news']");
	By btnStart= By.xpath("//*[@id='start']");
	By optionContninueReading= By.xpath("//a[contains(text(),'Continue reading')][@id='answer_1']");
	By txtCorrectAnswer= By.xpath("//h5[contains(text(),'That is correct!')]");
	By btnContinue= By.xpath("//*[@id='continue']");
	By optionReasearchIntoPersonal= By.xpath("//a[contains(text(),'Research into personal protective equipment')][@id='answer_2']");
	By optionMoveToAnotherSeat= By.xpath("//a[contains(text(),'Move to another seat immediately and report it to')][@id='answer_2']");
	By optionTakeNiceTripToUK= By.xpath("//a[contains(text(),'Take a nice trip to UK, you will be back')][@id='answer_1']");
	By txtWrongAnswer= By.xpath("//h5[contains(text(),'Oh no!')]");
	By btnGoHomeStart= By.xpath("//button[contains(text(),'Go home and start again')]");
	By userScore= By.xpath("//*[@id='user_score']");
	By leaderBoardUser=By.xpath("(//p[@id='showData']//td[1])[1]");
	By leaderBoardUserScore=By.xpath("(//p[@id='showData']//td[2])[1]");
	static By leaderBoardList=By.xpath("//th[contains(text(),'username')]");
	By loginFailed= By.xpath("//*[@id='login_popup']");
	
	
	
	
	
	
	
	WebDriverWait wait=new WebDriverWait(driver, 20);
	
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);		
	}
	
	public boolean login(String un, String pwd,ExtentTest eTest)
	{		
		setText(wusername,un,eTest,"User Name as "+un);
		setText(wpwd,pwd,eTest,"Password");				
		clickElement(loginlnk,eTest,"Login option");
		
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(loginFailed));
			return false;
			
		}catch(Exception e)
		{
			return true;
		}
		
	}
	
	public void completeChallenge(ExtentTest eTest) throws Exception
	{	
			
		clickElement(startGameBtn,eTest,"Start Game button");				
		clickElement(optionEnterAtYourOwnRisk,eTest,"Option - Enter at your risk");
		clickElement(btnStart,eTest,"Start button");
		clickElement(optionContninueReading,eTest,"Option - Continue reading");		
		clickElement(btnContinue,eTest,"Continue button");
		clickElement(optionReasearchIntoPersonal,eTest,"Option - Reasearch into personal");
		clickElement(btnContinue,eTest,"Continue button");
		clickElement(optionMoveToAnotherSeat,eTest,"Option - move to another seat");
		clickElement(btnContinue,eTest,"Continue button");
		clickElement(optionTakeNiceTripToUK,eTest,"Take a nice trip to UK");
		clickElement(btnGoHomeStart,eTest,"Go to Home/Start");
		
	}
	
	public void getScore()
	{        
		String strUserScore="";			
		strUserScore=getTextElement(userScore);
		System.out.println("UserScore :"+strUserScore);
	}
	
	public void navigateToLoginOption(ExtentTest eTest)
	{
		
		clickElement(loginOption,eTest,"LoginOption");
	}
	
	public void launchApplication()
	{
		driver.get("https://responsivefight.herokuapp.com/");
	}
	
	public void clickElement(By webElement,ExtentTest eTest,String strMsg)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		driver.findElement(webElement).click();
		eTest.log(Status.INFO, "Click on "+strMsg);
		
	}
	
	public void setText(By webElement,String value,ExtentTest eTest,String strMsg)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		driver.findElement(webElement).sendKeys(value);
		eTest.log(Status.INFO, "Set "+strMsg);
	}
	
	public String getTextElement(By webElement)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		return driver.findElement(webElement).getText();
	}
	
	public void waitForLeaderBoard()
	{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(leaderBoardList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String getLeaderBoardScore(String strUser,ExtentTest eTest) throws Exception
	{
		//String filePath="";
		
		
		driver.get(prop.getProperty("leaderBoardurl"));		
		String userScore=driver.findElement(By.xpath("//td[contains(text(),'"+strUser+"')]/following-sibling::td")).getText();
		//filePath=TestUtil.takeScreenshotAtEndOfTest();
		//eTest.addScreenCaptureFromPath(filePath," Create new user and update leaderboard");		
		wait.until(ExpectedConditions.visibilityOfElementLocated(leaderBoardList));
		//eTest.log(Status.INFO, "In the LoginLeaderaboard Page Test Report");
		return userScore;

		
	}
	
	
	
	
	
	

}

