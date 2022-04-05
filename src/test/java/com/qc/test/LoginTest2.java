package com.qc.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest2 extends BaseClass2{
	@BeforeSuite
	public void setup() throws IOException {
	doSetup();
	}
	@BeforeMethod
	public void getXpath() {
		email = driver.findElement(By.xpath("//input[@type='text']"));
		email.clear();
		password = driver.findElement(By.xpath("//input[@type='password']"));
		password.clear();
		submit = driver.findElement(By.xpath("//button[@type='submit']"));
	}
	@Test(dataProvider = "LoginData")
	public void doLogin( String testName, String uName, String upassword,String testresult) {
		email.sendKeys(uName);
		password.sendKeys(upassword);
		submit.click();
	
	}
	@AfterMethod
	public void doAssert() throws InterruptedException {
		Thread.sleep(2000);
		actResult = driver.getTitle();
		if (actResult.equals("Queue Codes | Log in")) {
			expResult = "Queue codes | Log in";
		} else {
			expResult = "Queue Codes | Dashboar";
			dologout();
		}
		sa.assertEquals(actResult, expResult);
	
	}

	public void dologout() {
		logout = driver.findElement(By.id("hlogout"));
		logout.click();
	}
	@AfterSuite
	public void CloseBrowser() {
	tearDown();
	}



}
