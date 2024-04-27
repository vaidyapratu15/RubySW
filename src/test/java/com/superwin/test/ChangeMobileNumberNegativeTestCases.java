package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class ChangeMobileNumberNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadTiming = (Long) js
				.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		// Print the page load timing
		extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
	}

	@Test(priority = 1)
	public void login() throws IOException, InterruptedException {
		Thread.sleep(4000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());

		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + accessToken);
		soft.assertNotEquals(accessToken, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyChangeMobileNumberBtn() {
		changepass.clickOnChangeNumber();
	}

	@Test(priority = 5)
	public void changeMobileNumberForInvalidMobileNumber() throws InterruptedException, IOException {
		String actualMsg = changepass.setNewNumber("7249");
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getInvalidMobileNo());
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, false);
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	public void changeMobileNumberForExistingNumber() throws InterruptedException, IOException {
		String actualMsg = changepass.setNewNumber(GetGsonTestData.getData().getUsername());
		soft.assertEquals(actualMsg, null);
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getExistingNumber());
		
	}

	@Test(priority = 7)
	public void changeMobileNumberForIncorrectOTP1() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);

		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);

		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("456556456565");
		soft.assertEquals(actualMsg2, "");

		String actualMsg3 = changepass.setNewOtp("33454545");
		soft.assertEquals(actualMsg3, "");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, true);
		Thread.sleep(1000);
		String actualMsg4 = changepass.popup();
		soft.assertEquals(actualMsg4, GetGsonTestData.getConfigData().getInvalidOTP());
	}

	@Test(priority = 8)
	public void changeMobileNumberForOTPIncludingLetters() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);

		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);

		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("cxdbcvbvc");
		soft.assertEquals(actualMsg2, "Not Able To Enter Old OTP..!!!");

		String actualMsg3 = changepass.setNewOtp("bxc");
		soft.assertEquals(actualMsg3, "Not Able To Enter New OTP..!!!");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, false);
		Thread.sleep(1000);
	}

	@Test(priority = 9)
	public void changePwdForInvalidOTP() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);

		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);

		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("123");
		soft.assertEquals(actualMsg2, "OTP must be 6 digits");
		Thread.sleep(1000);
		String actualMsg3 = changepass.setNewOtp("6565");
		soft.assertEquals(actualMsg3, "OTP must be 6 digits");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, false);
		Thread.sleep(1000);
	}

	// @Test(priority = 10)
	public void verifyChangeNumberAE() throws Exception {
		changepass.contryCode("+971");
		String number = changepass.getNewNumber();
		changepass.setNewNumber(number);
		changepass.clickNext();
		Thread.sleep(1000);
		changepass.popup();

		String oldotp = changepass.getOldOtp();
		changepass.setOldOtp(oldotp);

		String newotp = changepass.getNewOtp();
		changepass.setNewOtp(newotp);

		changepass.submit();
		Thread.sleep(1000);
		changepass.popup();
	}

	// @Test(priority = 11)
	public void verifyChangeNumberIN() throws Exception {
		String number = changepass.getNewNumber();
		changepass.setNewNumber(number);
		changepass.clickNext();
		Thread.sleep(1000);
		changepass.popup();

		String oldotp = changepass.getOldOtp();
		changepass.setOldOtp(oldotp);

		String newotp = changepass.getNewOtp();
		changepass.setNewOtp(newotp);
		changepass.submit();
		Thread.sleep(1000);
		changepass.popup();
	}

	@Test(priority = 12)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 13)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token Before Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();

	}

}
