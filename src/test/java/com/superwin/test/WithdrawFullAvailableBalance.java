package com.superwin.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class WithdrawFullAvailableBalance extends BaseTest {

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
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}
	
	@Test(priority = 4)
	public void getWalletAmt() throws InterruptedException {
		withdrawalpage.getWalletDetails();
	}

	@Test(priority = 5)
	public void verifyWithdraw() throws InterruptedException {
		String actualData = withdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
	}    
	
	@Test(priority = 6)
	public void verifyWithdrawFullAmt() throws InterruptedException, FileNotFoundException {
		String balance = withdrawalpage.getAmountToBeWithdrawal();
		withdrawalpage.enterAmt(balance);
		String actualData = withdrawalpage.popUp();
		soft.assertEquals(actualData, GetGsonTestData.getConfigData().getAmtExceed());
		
		String actualData1 = withdrawalpage.proceedWithdrawal();
		soft.assertEquals(actualData1, "Proceed Button Is Not Clickable..!!!");
		
		driver.navigate().refresh();
	}

    @Test(priority = 7)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 8)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}
	
}
