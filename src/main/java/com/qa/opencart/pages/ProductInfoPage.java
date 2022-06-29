package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	Map<String, String> productInfoMap;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImag = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.xpath("//input[@name='quantity']");
	private By addToCart = By.xpath("//button[@id='button-cart']");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");
	private By cart = By.cssSelector("div#cart button.dropdown-toggle");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		return eleutil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	public int getProductImageCount() {
		return eleutil.waitForElementsVisible(productImag, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}

	public Map<String,String> getproductInformation() {
		productInfoMap = new HashMap<String, String>();
		
		productInfoMap.put("name", getProductHeaderName());
		
		getMetaData();// Encapsulation achieved
		getPriceData();
		productInfoMap.forEach((k,v) -> System.out.println(k+ ":" + v));
		return productInfoMap;
	
	}
	private void getMetaData() {
		//meta
		List<WebElement> metaDataList = eleutil.getElements(productMetaData);
		System.out.println("The MEta Data Product List is "+ metaDataList);
		
		for(WebElement e : metaDataList) {
			String[] meta = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}
	
	private void getPriceData() {

		//price
		List<WebElement> priceList = eleutil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim(); //$2000.00
		String ExTaxprice = priceList.get(1).getText().trim(); 
		
		productInfoMap.put("price", price);//key is the userdefined
		productInfoMap.put("Ex-price", ExTaxprice);
	}
	
	public String getPageInfoPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String  pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("=============================\n" + pageInnerText +"\n=======================" );
		return pageInnerText;
	}
	
	public ProductInfoPage addQuantity(String qty) {
		eleutil.doSendKeys(quantity, qty);
		return this;
		
	}
	
	public ProductInfoPage clickAddToCart() {
		eleutil.doClick(addToCart);
		return this;
	}
	
	public String getCartSuccMesg() {
		return eleutil.waitForElementVisible(cartSuccessMessage, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	public String getCartItemText() {
		return eleutil.doGetText(cart);
	}
	
}
