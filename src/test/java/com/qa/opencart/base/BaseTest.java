package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	public DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public AccountPage accPage;
	public SearchResultPage searchResPage;
	protected ProductInfoPage proInfoPage;
	protected RegisterPage registerPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop(); 
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		SoftAssert softAssert = new SoftAssert();	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
