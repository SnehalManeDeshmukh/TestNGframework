package com.qc.test;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Registration extends BaseClass2{
	@BeforeSuite
	public void setup() throws IOException {
		doSetup();
	}
	@BeforeTest
	public void ClickOnRegistrationLink() {
		regLink=driver.findElement(By.linkText("Register a new membership"));
		regLink.click();
	}
	@BeforeMethod
	public void getXpathforRegistration() {
		rName=driver.findElement(By.xpath("//input[@id='name']"));
		rName.clear();
		rMobile=driver.findElement(By.xpath("//input[@id='mobile']"));
		rMobile.clear();
		rEmail=driver.findElement(By.xpath("//input[@id='email']"));
		rEmail.clear();
		rPassword=driver.findElement(By.xpath("//input[@id='password']"));
		rPassword.clear();
		submit=driver.findElement(By.xpath("//button[@type='submit']"));
	}
	@Test(dataProvider="registerData")
	public void doRegister(String uName,String uMoblie,String uEmail,String uPassword)
	{
		name=uName;
		mobile=uMoblie;
		email1=uEmail;
		password1=uPassword;
		rName.sendKeys(uName);
		rMobile.sendKeys(uMoblie);
		rEmail.sendKeys(uEmail);
		rPassword.sendKeys(uPassword);
		submit.click();
	}
	@AfterMethod
	public void doAssertForRegistration() throws InterruptedException {
		Thread.sleep(2000);
		if (name!=""&& mobile!=""&& email1!=""&& password1!="") {
			Alert alt=driver.switchTo().alert();
			alt.accept();
			logLink=driver.findElement(By.linkText("I already have a membership"));
			logLink.click();
		} else {
			actResult=driver.getTitle();
			expResult = "Queue Codes | Registration Page";
			Assert.assertEquals(actResult, expResult);
		}
	
	}
	@AfterSuite
	public void CloseBrowser() {
	tearDown();
	}

}
