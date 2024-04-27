package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

import java.io.IOException;

public class EditProfileNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test
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
		Thread.sleep(2000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
		//soft.assertAll();
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
		//soft.assertAll();
	}

	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void clickOnEditProfile() {
		editprofile.editProfile();
	}

	@Test(priority = 5)
	public void editProfileForFirstOTPLessThan6Digit() throws IOException, InterruptedException {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("65");
		soft.assertEquals(actualMsg1, "OTP must be 6 digits");
		//soft.assertAll();
	}

	@Test(priority = 6)
	public void editProfileForFirstWrongOTP() throws IOException, InterruptedException {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("655454654545");
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getOtpVerificationFail());
	//	soft.assertAll();
	}

	@Test(priority = 7)
	public void editProfileForFirstOTPContainsLetters() throws IOException, InterruptedException {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("df");
		soft.assertEquals(actualMsg1, "Not Able To Enter First OTP..!!!");
		//soft.assertAll();
	}

	@Test(priority = 8)
	public void editProfileForAgeValidation() throws Exception {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		System.out.println(otp);
		editprofile.setOtp(otp);
		editprofile.editDOB("2021", "OCT", "1");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getAgePopup());
	//	soft.assertAll();
	}

	@Test(priority = 9)
	public void editProfileForSecondOTPLessThan6Digit() throws Exception {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("76");
		soft.assertEquals(actualMsg2, "OTP must be 6 digits");
		//soft.assertAll();
	}

	@Test(priority = 10)
	public void editProfileForSecondOTPGreaterThan6Digit() throws Exception {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("6545433243");
		soft.assertEquals(actualMsg2, "Not Able To Enter Second OTP..!!!");
		//soft.assertAll();
	}

	@Test(priority = 11)
	public void editProfileForSecondOTPContainsLetters() throws Exception {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("fgcgvcvbb");
		soft.assertEquals(actualMsg2, "Not Able To Enter Second OTP..!!!");
		//soft.assertAll();
	}

	@Test(priority = 12)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 13)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
