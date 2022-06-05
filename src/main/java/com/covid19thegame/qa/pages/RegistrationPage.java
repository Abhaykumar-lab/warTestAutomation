package com.covid19thegame.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.covid19thegame.qa.base.TestBase;
import com.covid19thegame.qa.util.TestUtil;

public class RegistrationPage extends TestBase
{


	By registrationLink = By.xpath("//a[@class='btn'][contains(text(),' Register')]");
	By reg_uname= By.xpath("//*[@id='uname']");
	By reg_pwd= By.xpath("//*[@id='pwd']");
	By reg_repPwd= By.xpath("//*[@id='psw-repeat']");
	By reg_signUpBtn= By.xpath("//*[@id='signupbtn']");
	By login_title= By.xpath("//*[@id='login_title']");
	By userAlreadyExistsMsg= By.xpath("//*[@id='popup']");
	
	
	WebDriverWait wait=new WebDriverWait(driver, 10);
	
	public RegistrationPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean registerUser(String userName, String userPwd,ExtentTest eTest) throws Exception{
		
		String filePath="";
		clickElement(registrationLink,eTest,"Registration option");		
		eTest.log(Status.PASS, "Click on Registration Link");
		setText(reg_uname,userName,eTest,"User Name as "+userName);		
		eTest.log(Status.PASS, "Enter User Name : "+userName);
		setText(reg_pwd,userPwd,eTest,"Password");		
		eTest.log(Status.PASS, "Password enterered successfully");
		setText(reg_repPwd,userPwd,eTest,"Repeat password");		
		eTest.log(Status.PASS, "Password re enterered successfully");
		clickElement(reg_signUpBtn,eTest,"Sign Up Button");
		eTest.log(Status.PASS, "Clicked on sign up option");
		
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(userAlreadyExistsMsg));
			return false;
			
		}catch(Exception e)
		{
			return true;
		}
		
	}
	
	public boolean verifyLoginPage() throws Exception
	{
		//String strFilePath=TestUtil.takeScreenshotAtEndOfTest();		
		return isDisplayedElement(login_title);
	}
	

	public boolean verifyUserAlreadyExists()
	{		
		return isDisplayedElement(userAlreadyExistsMsg);
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
	
	public boolean isDisplayedElement(By webElement)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		return driver.findElement(webElement).isDisplayed();
	}
	
	
		

	
}
