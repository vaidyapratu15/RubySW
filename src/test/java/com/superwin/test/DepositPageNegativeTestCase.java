package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class DepositPageNegativeTestCase extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void getBrowserLink() throws InterruptedException {
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
		org.testng.Assert.assertEquals(actualbtn, true);

		//loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyDeposit() throws InterruptedException {
		deposit.checkWalletAmount();
		deposit.depositNow();
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void verifyNegativeDepositThroughUPI() throws InterruptedException {
		deposit.depositThroughUPI("100", "sdfghjuio876tre");
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void verifyNegativeDepositThroughNetBanking() throws InterruptedException {
		deposit.depositThroughNetBanking("101", "hgfdsfghjk");
		Thread.sleep(2000);
	}

//	// @Test(priority = 7)
////	public void verifyNegativeDepositThroughBankDeposit() throws InterruptedException, IOException, AWTException {
////		deposit.depositThroughBankDeposit("101");
////	}
//
	@Test(priority = 8)
	public void verifyNegativeDepositThroughAstropay() throws InterruptedException {
		deposit.depositThroughAstropay("100", "876543567iuyghfdsx");
		Thread.sleep(2000);
	}

	@Test(priority = 9)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 10)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

//	@AfterMethod
//	public void onTestFailure(ITestResult result) throws InterruptedException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			String testcasename = result.getMethod().getMethodName();
//			Utilities.captureScreenshot(driver, testcasename + ".jpg");
//		}
//	}
}
