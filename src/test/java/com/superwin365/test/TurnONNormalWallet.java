package com.superwin365.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TurnONNormalWallet extends BaseTest {

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
		loginpage.setUsername("9975105804");
		loginpage.setPassword("Pass@123");

		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);
		
		soft.assertAll();
	}

//
//	@Test(priority = 3)
//	public void turnONNormalWallet() throws InterruptedException {
//		String actualMsg = tripleWallet.turnONNormalWallet();
//		soft.assertEquals(actualMsg, "Normal Wallet Turned ON..!!!");
//		
//		soft.assertAll();
//		
//		Thread.sleep(30000);
//	}
//
	@Test(priority = 4)
	public void fetchWalletDetails() {
		tripleWallet.getWalletAmt();

		String actualMsg = tripleWallet.clickOnProfileBtn();
		soft.assertEquals(actualMsg, "Profile Button Is Clickable...!!!");

		String actualMsg1 = tripleWallet.getNormalWalletText();
		soft.assertEquals(actualMsg1, "Able To Fetch Normal Wallet Text...!!!");

		String actualMsg2 = tripleWallet.getNormalWalletDetails();
		soft.assertEquals(actualMsg2, "Able To Fetch Wallet Amount and Net Exposure Amount...!!!");

		String actulMsg3 = tripleWallet.getNormalWalletAvailableWithdrawal();
		soft.assertEquals(actulMsg3, "Able To Fetch Available Withdrawal Balance...!!!");

		soft.assertAll();
	}
//	
//	@Test(priority = 5)
//	public void clickOnBettingPnLBtn() {
//		tripleWallet.clickOnBettingPnL();
//	}
	
	@Test(priority = 6)
	public void fetchBettingPnLDetails() throws InterruptedException {
		Thread.sleep(2000);
		tripleWallet.fetcgBettingPnLDetails();
	}

	

}
