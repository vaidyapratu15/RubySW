package com.superwin.admin.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class AdminPanelAddTelegramLink extends Admin_BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void verifyLoginPositiveTc() throws Exception {
		String username = adminlogin.getUsername();
		adminlogin.setUsername(username);

		adminlogin.clickNext();

		Thread.sleep(10000);
		
		String otp = adminlogin.getOtp();
		adminlogin.setOtp(otp);

		String password = adminlogin.getPassword();
		adminlogin.setPassword(password);

		adminlogin.login();
		
		Thread.sleep(10000);
	}

	@Test(priority = 1)
	public void checkAccessTokenAfterLogin() throws IOException, InterruptedException {
		String beforelogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token Before Logout : " + beforelogout);
		soft.assertNotEquals(beforelogout, null);
		Thread.sleep(10000);
		GetGsonTestData.writeAccessToken(beforelogout);
	}

	@Test(priority = 2)
	public void clickOnSystemBtn() {
		String actualData = telegram.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");
	}

	@Test(priority = 3)
	public void clickOnSystemConfigBtn() {
		String actualData = telegram.clickOnSystemConfig();
		soft.assertEquals(actualData, "System Config Button Is Clickable..!!!");
	}

	@Test(priority = 4)
	public void clickOnTelegramSettingBtn() {
		String actualData = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData, "Telegram Setting Button Is Clickable..!!!");
	}
	
	@Test(priority = 5)
	public void clickOnCustomToggle() {
		telegram.turnedON_Telegram();
	}
	
	@Test(priority = 6)
	public void verifyTelegramLink() {
		String link = telegram.getTelegramLink();
		String actualData = telegram.setTelegramLink(link);
		soft.assertEquals(actualData, "Telegram Link Added Suceesfully...!!!");
	}

	@Test(priority = 7)
	public void clickOnAddLinkBtn() {
		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, true, " ");
	}

	@Test(priority = 8)
	public void verifyAddedLink() {
		String actualData = telegram.verifyAddedLink();
		soft.assertEquals(actualData, "Link Added Successfully...!!!");
	}

	@Test(priority = 9)
	public void verifyUpdateBtn() {
		String actualData = telegram.update();
		soft.assertEquals(actualData, "Update Button Is Clickable..!!!");
	}

	@Test(priority = 10)
	public void verifyUpdatedLink() {
		telegram.verifyUpdatedLink();
	}

	@Test(priority = 11)
	public void verifyToasterMessage() {
		String actualData = telegram.getToasterMsg();
		soft.assertEquals(actualData, "Telegram settings updated successfully");
	}
}
