package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.gson_Model.GetGsonTestData;

import java.io.IOException;

public class ForgotPasswordNegativeTestCases extends BaseTest {

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
	public void verifyForgotPwdForWrongNumber() throws IOException, InterruptedException {
	    Thread.sleep(10000);
		forgotpassword.forgotPassword();

		forgotpassword.setUsernumber("987654325678976543");

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getUserNotFound());

		driver.navigate().back();
		
	}

	@Test(priority = 2)
	public void verifyForgotPwdForWrongOTP() throws IOException, InterruptedException {
		forgotpassword.forgotPassword();
		
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("675443");
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg2 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg2, GetGsonTestData.getConfigData().getIncorrectVerificationCode());
		
		Thread.sleep(1000);
		
		driver.navigate().back();;
	}

	@Test(priority = 3)
	public void verifyForgotPwdForOTPLessThan6Digit() throws IOException, InterruptedException {
		forgotpassword.forgotPassword();
		
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		org.testng.Assert.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("123");
		org.testng.Assert.assertEquals(actualMsg1, "OTP must be 6 digits");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		org.testng.Assert.assertEquals(actualBtn, false);
		
		Thread.sleep(1000);
		
		driver.navigate().back();
	
	}

	@Test(priority = 4)
	public void verifyForgotPwdForOTPContainsOnlyLetters() throws IOException, InterruptedException {
		forgotpassword.forgotPassword();
		
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		org.testng.Assert.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("vcbvcbdvcgdfvd");
		org.testng.Assert.assertEquals(actualMsg1, "Not Able To Enter OTP..!!!");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);
		
		Boolean actualBtn = forgotpassword.changePassword();
		org.testng.Assert.assertEquals(actualBtn, false);

		Thread.sleep(1000);
		driver.navigate().back();
		
	}

	@Test(priority = 5)
	public void verifyForgotPwdForOTPGreaterThan6Digit() throws IOException, InterruptedException {
		forgotpassword.forgotPassword();
		
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("12365644545");
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg2 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg2, GetGsonTestData.getConfigData().getIncorrectVerificationCode());

		Thread.sleep(1000);
		driver.navigate().back();
		
	}

	@Test(priority = 6)
	public void verifyForgotPwdForUnmatchedPassword() throws Exception {
		forgotpassword.forgotPassword();
		
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.getOtp();
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		String actualErrorMsg = forgotpassword.setConfirmPassword("Pass@123");
		soft.assertEquals(actualErrorMsg, "Password and Confirm Password must match");

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, false);
		Thread.sleep(1000);
		driver.navigate().back();
	
	}

	// @Test(priority = 7)
	public void verifyForgotPwdForWrongOTPWithResendCode() throws Exception {
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		forgotpassword.popup();

		forgotpassword.setOtp("123456");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		forgotpassword.changePassword();
		forgotpassword.succPopup();

		forgotpassword.resendCode();
		forgotpassword.popup();

		String otp = forgotpassword.getOtp();
		forgotpassword.setOtp(otp);

		forgotpassword.changePassword();
		forgotpassword.succPopup();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(2000);
	}

}
