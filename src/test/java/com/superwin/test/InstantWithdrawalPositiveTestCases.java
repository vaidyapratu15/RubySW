package com.superwin.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class InstantWithdrawalPositiveTestCases extends BaseTest  {
	
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
	public void login() throws IOException {
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
		extentTest.info("Access Token After Login : " + afterLogin);
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void getWalletAmt() throws InterruptedException {
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 5)
	public void verifyInstantWithdraw() throws InterruptedException {
		String actualData = instantwithdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = instantwithdrawalpage.instantWithdraw();
		soft.assertEquals(actualData1, "InstantWithdraw Is Clickable..!!!");
	}

	@Test(priority = 6)
	public void WithdrawValidAmt() throws InterruptedException, FileNotFoundException {
		instantwithdrawalpage.enterAmt("1000");
		String actualData = instantwithdrawalpage.proceedWithdrawal();
		soft.assertEquals(actualData, "Proceed Button Is Clickable..!!!");
		Thread.sleep(2000);
		String actualData1 = instantwithdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonTestData.getConfigData().getTransInitiated());
	}

	@Test(priority = 7)
	public void checkWithdrawalReq() throws InterruptedException {
		// loginpage.profileClick();
		//withdrawalpage.myTransactions();
		Thread.sleep(1000);
		String actualData = instantwithdrawalpage.checkWithdrawalReq();
		soft.assertEquals(actualData, "withdrawal_req Button Is Clickable..!!!");
	}

	@Test(priority = 8)
	public void getWalletAmtAfterWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 9)
	public void cancelWithdrawalReq() throws InterruptedException, FileNotFoundException {
		String actualData = instantwithdrawalpage.cancelWithdrawalReq();
		soft.assertEquals(actualData, "Cancel Withdrawal_req Button Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = instantwithdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonTestData.getConfigData().getTransCancelled());
		Thread.sleep(1000);
	}

	@Test(priority = 10)
	public void getWalletAmtAfterCancellingWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		Thread.sleep(1000);
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 11)
	public void logout() {
		//loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 12)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
	}

}
