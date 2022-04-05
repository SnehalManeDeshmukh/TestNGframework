package com.qc.test;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import com.qc.utils.TestsUtils2;

public class BaseClass2 {
	TestsUtils2 test=new TestsUtils2();
	Properties prop;
	WebDriver driver;
	WebElement email, password, submit, logout,regLink,rName,rMobile,rEmail,rPassword,logLink;
	SoftAssert sa = new SoftAssert();
	String actResult, expResult,name,mobile,email1,password1;

	public void doSetup() throws IOException {
		prop=test.readPropData();
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty(prop.getProperty("WebDriverKey"),prop.getProperty("WebDriverValue"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.get(prop.getProperty("SiteURL"));
	} 
	@DataProvider
	public Object[][]LoginData() throws IOException {
	return test.readExcelData("LoginSheet");
		
	};
	@DataProvider
	public Object [][]registerData() throws IOException{
		return test.readExcelData("RegisterSheet");
	}
	public void tearDown() {
		driver.close();
		sa.assertAll();
	}


}
