package com.backendtest.superwinbook.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonSportsTestData;


public class SuperwinBookUserLoginFromSuperwin extends Superwin_BaseTest{
	
	SoftAssert soft = new SoftAssert();
	
	@Test
	public void verifyLoginPositiveTc() throws IOException, InterruptedException {
		extentTest.info("Base URL : " + driver.getCurrentUrl());
		
		
		loginpage.setUsername(GetGsonSportsTestData.getBookUserData().getMobileNO());
		loginpage.setPassword(GetGsonSportsTestData.getBookUserData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		org.testng.Assert.assertEquals(actualbtn, true);
		
		Thread.sleep(1000);
	}
	
	@Test(priority = 1)
	public void pageRedirectedTo() throws InterruptedException {
        extentTest.info("Page redirected to : " +driver.getCurrentUrl());
		
		Thread.sleep(1000);
	}
	
	@Test(priority = 2)
	public void acceptCookies() throws InterruptedException {
		signuppage.acceptCookie();
		signuppage.clickCheckBox();
		signuppage.acceptAndContinue();
		
		Thread.sleep(1000);
	}
	
	@Test(priority = 3)
	public void checkAccessTokenAfterLogin() {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + accessToken);
		soft.assertNotEquals(accessToken, null);
	}
	
	@Test(priority = 4)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 5)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getKeyValueFromLocalStorage(driver, "accessToken");
		extentTest.info("Access Token Before Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();

	}
}
