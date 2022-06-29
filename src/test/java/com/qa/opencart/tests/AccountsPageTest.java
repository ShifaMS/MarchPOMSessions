package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.pages.ProductInfoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic 1003 : Account PAge Valdiations-----------------")
@Story("Story 20003 : Perform all actions on the Account Page--------------------")

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	
	public void accsetup() {
		 accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@Description("Account page Title Test-----------------")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void acctPageTitleTest() {
		String actTitle = accPage.getAcctPageTitle();
		System.out.println("Acual Account Page Title is " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCT_PAGE_TITLE);
	}
	
	@Test
	public void acctPageURLTest() {
		String actURL = accPage.getAcctPageURL();
		System.out.println("Acual Account Page URL is " + actURL);
		Assert.assertEquals(actURL, Constants.ACCT_PAGE_URL_FRACTION);
	}
	
	@Test
	public void accPageLogoTest() {
		Assert.assertTrue(accPage.isAcctPageLogoPresent());
	}
	
	@Description("Account page Section Test-----------------")
	@Severity(SeverityLevel.MINOR)
	
	@Test
	public void acctPageSectionTest() {
		List<String> acctPageSectionList = accPage.getAcctPageSectionList();
		System.out.println("Section List are " + acctPageSectionList);
		Assert.assertEquals(acctPageSectionList, Constants.EXPECTED_ACCT_SEC_LIST);
	}
	
	
	@Description("Account page Logout Test-----------------")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = false)
	public void logoutTest() {
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccMsg(),Constants.LOGOUT_SUCCESS_MSG);
	}
	
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey) throws InterruptedException {
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultCount()>0);
	}
	
	
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"}
			
		};
	}
	@Test (dataProvider = "getProductName", enabled = false)
	public void selectProductTest(String searchKey, String productName) throws InterruptedException {
		searchResPage = accPage.doSearch(searchKey);
		proInfoPage = searchResPage.selectProduct(productName);
		String productHeaderName = proInfoPage.getProductHeaderName();
		System.out.println("Product ?Header " + productHeaderName);
		Assert.assertEquals(productHeaderName, productName);
		
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
		};
	}
	@Test (dataProvider = "getProductData", enabled = false)
	public void ProductImgTest(String searchKey, String productName, int productImgCount) throws InterruptedException {
		searchResPage = accPage.doSearch(searchKey);
		proInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(proInfoPage.getProductImageCount(), productImgCount);
		
	}
	
	@Test
	public void productInfoTest() throws InterruptedException {
		searchResPage = accPage.doSearch("Macbook");
		proInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = proInfoPage.getproductInformation();
		
		Assert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		Assert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		//softAssert.assertAll();
	}
	
	@Test(enabled = false)
	public void productInfoDescriptionTest() throws InterruptedException {
		searchResPage = accPage.doSearch("Macbook");
		proInfoPage = searchResPage.selectProduct("MacBook Pro");
		Assert.assertTrue(proInfoPage.getPageInfoPageInnerText().contains("Latest Intel mobile architecture"));
		Assert.assertTrue(proInfoPage.getPageInfoPageInnerText().contains("new Core 2 Duo MacBook Pro is over 50%"));
		Assert.assertTrue(proInfoPage.getPageInfoPageInnerText().contains("Connect. Create. Communicate."));
		//Assert.assertAll();
	}
	
	@Test(enabled = false)
	public void zaddToCartTest() throws InterruptedException {
		searchResPage = accPage.doSearch("Macbook");
		proInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMessg = proInfoPage
				.addQuantity("1")
							.clickAddToCart()
									.getCartSuccMesg();
		System.out.println("cart messg: " + actSuccessMessg);
		Assert.assertTrue(actSuccessMessg.contains("MacBook Pro"));
		String actCartItemText = proInfoPage.getCartItemText();
		Assert.assertTrue(actCartItemText.contains("1" + " item(s)"));
	}
	
	

}
