package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class WithdrawalNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadTiming = (Long) js.executeScript(
            "return performance.timing.loadEventEnd - performance.timing.navigationStart;"
        );
        // Print the page load timing
        extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
	}
	
	@Test(priority = 1)
	public void login() throws IOException, InterruptedException {
		Thread.sleep(2000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		org.testng.Assert.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}
	
	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyWithdraw() throws InterruptedException {
		String actualData = withdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
	}

	@Test(priority = 5)
	public void verifyWithdrawForWrongOTP() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("123678");
		soft.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, GetGsonTestData.getConfigData().getOtpVerificationFail());
		Thread.sleep(1000);
		
	}

	@Test(priority = 6)
	public void verifyWithdrawForOTPLessThan6Digit() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("123");
		soft.assertEquals(actualMsg1, "OTP Must Be 6 Digits");
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);
		
	}

	@Test(priority = 7)
	public void verifyWithdrawForOTPContainsOnlyLetters() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("jdcbvbfbvdbvv");
		soft.assertEquals(actualMsg1, "Not Able To Enter OTP..!!!");
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);
	
	}

	@Test(priority = 8)
	public void verifyWithdrawForInvalidCred() throws Exception {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = withdrawalpage.getOtp();
		String actualMsg1 = withdrawalpage.setOtp(otp);
		soft.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("1231231");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("123456789");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		withdrawalpage.addAccount();
		Thread.sleep(1000);

	}

	@Test(priority = 9)
	public void verifyWithdrawForInvalidIFSC() throws Exception {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualData = withdrawalpage.otpPopup();
		soft.assertEquals(actualData, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = withdrawalpage.getOtp();
		String actualMsg1 = withdrawalpage.setOtp(otp);
		org.testng.Assert.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KOBK0000266");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		withdrawalpage.addAccount();
		Thread.sleep(1000);
	}

	
	@Test(priority = 10)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}
}
