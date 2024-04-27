package com.superwin.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class LoginPositiveTestCases extends BaseTest {

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

	// @Test(priority = 1)
	public void verifyLoginPositiveTcThroughContryCode() throws Exception {
		loginpage.contryCode("AE - United Arab Emirates (+971)");

		loginpage.setUsername(GetGsonTestData.getUserData().getUsername());
		loginpage.setPassword(GetGsonTestData.getUserData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		Thread.sleep(1000);

		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.walletAmount(driver);

		loginpage.profileClick();

		loginpage.logout();

		loginpage.downloadAppPopUp();

		Thread.sleep(1000);
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

	@Test(priority = 2)
	public void verifyLoginPositiveTc() throws Exception {
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		Thread.sleep(1000);

		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
		
		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);

		loginpage.profileClick();

		loginpage.logout();

		Thread.sleep(1000);

		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

	@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			driver.navigate().refresh();
		}
	}
}
