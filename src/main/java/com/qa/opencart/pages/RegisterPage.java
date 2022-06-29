package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//By locators
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By fax = By.id("input-fax");
	
	private By address = By.id("input-address-1");
	private By city = By.id("input-city");
	private By postCode = By.id("input-postcode");
	private By country = By.id("input-country");
	private By state = By.id("input-zone");
	
	private By password = By.id("input-password");
	private By confirmpswd = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	


	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	public boolean registerUser(String firstname, String lastName, String email,String telephone, String password,String subscribe) throws InterruptedException {
		WebElement firstName_ele = eleutil.waitForElementVisible(this.firstName, Constants.DEFAULT_ELEMENT_TIME_OUT);
		firstName_ele.clear();
		firstName_ele.sendKeys(firstname);
		
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpswd, password);
		
		if(subscribe.equalsIgnoreCase("YES")) {
			eleutil.doClick(subscribeYes);
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		
		
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueButton);

		String successMesg = eleutil.waitForElementVisible(registerSuccessMesg, Constants.DEFAULT_TIME_OUT).getText();

		if (successMesg.contains(Constants.ACCOUNT_REGISTER_SUCCESS_MESSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		else {
			eleutil.doClick(logoutLink);
			
			eleutil.doClick(registerLink);
		}
		return false;
	}
		
	
}
