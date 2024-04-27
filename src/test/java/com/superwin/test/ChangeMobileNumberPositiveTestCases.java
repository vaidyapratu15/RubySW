package com.superwin.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class ChangeMobileNumberPositiveTestCases extends BaseTest {

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
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token Before Logout : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
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
	public void verifyChangeMobileNumber() throws Exception {

		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);

		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);

		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getVerifyOTP());

		String oldotp = changepass.getOldOtp();
		String actualMsg2 = changepass.setOldOtp(oldotp);
		soft.assertEquals(actualMsg2, "User Entered 6 Digit OTP..!!!");

		String newotp = changepass.getNewOtp();
		String actualMsg3 = changepass.setNewOtp(newotp);
		soft.assertEquals(actualMsg3, "User Entered 6 Digit OTP..!!!");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, true);
		Thread.sleep(1000);
		String actualMsg4 = changepass.popup();
		soft.assertEquals(actualMsg4, GetGsonTestData.getConfigData().getMobileNoChanged());

		GetGsonTestData.writeJson(number, GetGsonTestData.getData().getPassword());	
	}

	@Test(priority = 6)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 7)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();
	}

}
