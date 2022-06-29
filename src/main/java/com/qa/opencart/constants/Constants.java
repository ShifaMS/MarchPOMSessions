package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCT_PAGE_TITLE = "My Account"; 
	public static final String ACCT_PAGE_URL_FRACTION = "https://naveenautomationlabs.com/opencart/index.php?route=account/account"; 
	public static final String ACCT_PAGE_HEADER = "Your Store";
	public static final String LOGOUT_SUCCESS_MSG = "Account Logout";
	
	
	public static final List<String> EXPECTED_ACCT_SEC_LIST = 
			 Arrays.asList("My Account",
				"My Orders",
				"My Affiliate Account",
				"Newsletter");
 	
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int DEFAULT_ELEMENT_TIME_OUT = 10;
	public static final String ACCOUNT_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	/////////////////////////////
	public static final String REGISTER_SHEET_NAME = "register";
	

	; 

}
