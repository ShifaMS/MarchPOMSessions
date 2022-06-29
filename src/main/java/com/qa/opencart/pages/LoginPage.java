package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Epic("Epic 1002 : Login PAge Valdiations-----------------")
@Story("Story 20002 : Perform all actions on the Login Page--------------------")
public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//private By locators
	// page class are teh classic example of Encapsulation
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@value='Login']");
	private By forgotpswdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	private By logoutSuccessMsg = By.cssSelector("div#common-success h1"); 

	
	//public constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	
	//3. page Actions
	@Step("Getting Login Page Title.....................")
	public String getLogionPageTitle() {
		return eleutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("Getting Login Page URL-------------------------------")
	public String getLogionPageURL() {
		return eleutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step(" User is loged in with username {0} and Password {1}")
	public AccountPage doLogin(String un, String pw) {
		System.out.println("Login Credentails arev " + un+ " : "+pw);
		eleutil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		eleutil.doSendKeys(password, pw);
		eleutil.doClick(login);
		return new AccountPage(driver);// Next landing Page Class Object
		
	}
	
	@Story("Forgot Password Link -------------------------")
	public boolean isForgotPswdLinkExist() {
		return eleutil.doIsDisplayed(forgotpswdLink);
	}
	@Story("Register Password Link Test-------------------------")
	public boolean isRegisterLinkExist() {
		return eleutil.doIsDisplayed(registerLink);
	}


	@Story("Logout Password Link -------------------------")
	public String getLogoutSuccMsg() {
		return eleutil.waitForElementVisible(logoutSuccessMsg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		
	}
	
	public RegisterPage goToRegisterPage() {
		eleutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
}
