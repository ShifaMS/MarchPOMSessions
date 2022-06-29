package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	//Private Locators
	private By searchResult = By.cssSelector("div.product-layout.product-grid");
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	
	}
	
	public int getSearchResultCount() {
		return eleutil.waitForElementsVisible(searchResult, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productNameLink = By.linkText(productName);
		eleutil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}

}
