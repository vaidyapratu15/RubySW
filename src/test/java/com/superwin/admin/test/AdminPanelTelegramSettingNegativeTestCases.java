package com.superwin.admin.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class AdminPanelTelegramSettingNegativeTestCases extends Admin_BaseTest {

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
	public void verifyInvalidTelegramLink() {
		String actualData = telegram.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");

		String actualData1 = telegram.clickOnSystemConfig();
		soft.assertEquals(actualData1, "System Config Button Is Clickable..!!!");

		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");

		String link = "gfdhfgdhjfgbdfhvbfbvhfdgb";
		String actualData3 = telegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Invalid Link");

		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");

		driver.navigate().refresh();
	}

	@Test(priority = 3)
	public void verifyNullTelegramLink() {
	
		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");

		String link = "       ";
		String actualData3 = telegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Telegram Link is required");

		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");

		driver.navigate().refresh();
	}
	
	@Test(priority = 4)
	public void verifyInvalidDgTelegramLink() {
	
		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");

		String link = "gfdhfgdhjfgbdfhvbfbvhfdgb";
		String actualData3 = digiTelegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Invalid Link");

		Boolean actualBtn = digiTelegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");

		driver.navigate().refresh();
	}

	@Test(priority = 5)
	public void verifyNullDgTelegramLink() {
	
		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");

		String link = "       ";
		String actualData3 = digiTelegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Telegram Link is required");

		Boolean actualBtn = digiTelegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");

	}

}
