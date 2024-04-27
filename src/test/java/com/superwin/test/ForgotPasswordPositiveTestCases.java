package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.gson_Model.GetGsonTestData;

public class ForgotPasswordPositiveTestCases extends BaseTest {

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
	public void verifyForgotPassword() throws Exception {
		Thread.sleep(1000);
		forgotpassword.forgotPassword();
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());

		String otp = forgotpassword.getOtp();
		forgotpassword.setOtp(otp);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg1 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getPwdChanged());

		GetGsonTestData.writeJson(number, password);

		driver.navigate().refresh();
		soft.assertAll();
	}

}
