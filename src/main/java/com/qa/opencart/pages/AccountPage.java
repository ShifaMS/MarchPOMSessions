package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//Private By locators
	
	private By logo =  By.cssSelector("div#logo");
	private By logout  = By.linkText("Logout");
	private By sectionHeader = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	//Actions
	
	public String getAcctPageTitle() {
		return eleutil.waitForTitleIs(Constants.ACCT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAcctPageURL() {
		return eleutil.waitForUrlContains(Constants.ACCT_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	public boolean isAcctPageLogoPresent() {
		return eleutil.doIsDisplayed(logo);
	}
	public List<String> getAcctPageSectionList() {
		List<WebElement> secList = eleutil.getElements(sectionHeader);
		List<String> secValList = new ArrayList<String>();
		for(WebElement e : secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}
	
	public boolean isLogoutExist() {
		return eleutil.waitForElementVisible(logout, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();	
	}
	
	public LoginPage clickOnLogout() {
		if(isLogoutExist()) {
		eleutil.doClick(logout);
		}
		return new LoginPage(driver);
	}
	public boolean isSearchExist() {
		return eleutil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();	
	}

	public SearchResultPage doSearch(String searchKey) throws InterruptedException {
		if(isSearchExist()) {
			
			eleutil.doSendKeys(search, searchKey);
			eleutil.doClick(searchIcon);
			Thread.sleep(1000);
			return new SearchResultPage(driver);
		}
		return null;
	}
}
