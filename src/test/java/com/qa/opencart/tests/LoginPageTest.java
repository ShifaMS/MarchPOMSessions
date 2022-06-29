package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class LoginPageTest extends BaseTest{
	
	@Description("Login page Title Test-----------------")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginpageTitleTest() {
		String actTitle = loginPage.getLogionPageTitle();
		System.out.println("Login Page Title "+ actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("Login page URL Test-----------------")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginpageURLTest() {
		String actURL = loginPage.getLogionPageURL();
		System.out.println("Login Page URL "+ actURL);
		Assert.assertTrue(actURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		
	}
	@Description("Login page Forgot Test-----------------")
	@Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPswdLinkExist());
		
	}
	
	@Description("Login page RegisterLink Test-----------------")
	@Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
   	public void registerLinkExistTest() {
   		Assert.assertTrue(loginPage.isRegisterLinkExist());
   		
   	}
    
	@Description("Login page Logout Test-----------------")
	@Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5)
    public void loginTest() {
    	Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
    	.isLogoutExist());
    }
    
    
}
