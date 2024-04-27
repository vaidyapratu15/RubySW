package com.superwin365.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TurnOnBonusWallet extends BaseTest {

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
	public void login() throws IOException {
		loginpage.setUsername("9975105804");
		loginpage.setPassword("Pass@123");

		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);

		soft.assertAll();
	}

	@Test(priority = 2)
	public void clickOnProfileBtn() {
		String actualMsg = tripleWallet.clickOnProfileBtn();
		soft.assertEquals(actualMsg, "Profile Button Is Clickable...!!!");

		soft.assertAll();
	}

	@Test(priority = 3)
	public void turnONBonusWallet() throws InterruptedException {
		String actualMsg = bonusWallet.turnONBonusWallet();
		soft.assertEquals(actualMsg, "Bonus Wallet Turned ON...!!!");

		soft.assertAll();

		Thread.sleep(30000);
	}

	@Test(priority = 4, dependsOnMethods = "turnONBonusWallet")
	public void fetchWalletDetails() {
		tripleWallet.getWalletAmt();

		String actualMsg = tripleWallet.clickOnProfileBtn();
		soft.assertEquals(actualMsg, "Profile Button Is Clickable...!!!");

		String actualMsg1 = bonusWallet.getBonusText();
		soft.assertEquals(actualMsg1, "Able To Fetch The Bonus Text...!!!");

		String actualMsg2 = bonusWallet.getBonusWalletDetails();
		soft.assertEquals(actualMsg2, "Able To Fetch The Bonus Amount...!!!");

		String actualMsg3 = bonusWallet.getBonusNetExposureAmount();
		soft.assertEquals(actualMsg3, "Able To Fetch The Net Exposure Amount...!!!");

		soft.assertAll();
	}

	@Test(priority = 5)
	public void clickOnBonusInfoBtn() {
		String actualMsg = bonusWallet.clickOnBonusInfoBtn();
		soft.assertEquals(actualMsg, "Able To Click On Bonus Info Button...!!!");

		soft.assertAll();
	}

	@Test(priority = 6)
	public void checkVisibilityOfSportsBonusInfoBonus() {
		Boolean data = bonusWallet.visibilityOfBonusInfoPopup();
		soft.assertEquals(data, true);

		soft.assertAll();
	}

	@Test(priority = 7)
	public void fetchSportsBonusInfo() {
		bonusWallet.fetchBonusInfoTitle();
		String actualTitle1 = bonusWallet.fetchBonusInfo();
		soft.assertEquals(actualTitle1, "Able To Fetch The Bonus Info...!!!");

		soft.assertAll();
	}

	@Test(priority = 8)
	public void clickOnSportsBonusWalletDropdown() throws InterruptedException {
		bonusWallet.clickOnBonusWalletDropdown();
	}

	@Test(priority = 9)
	public void fetchSportsBonusWalletDetails() {
		bonusWallet.getBonusDetails();
	}

	// @Test(priority = 10)
	public void clickOnTermsAndConditionDetails() {
		bonusWallet.clickOnTermsAndConditions();
	}

	@Test(priority = 11)
	public void clickOnViewMoreBtn() {
		bonusWallet.clickOnViewMore();
	}

	@Test(priority = 12)
	public void fetchPendingSportsBonusTransferDetails() {
		bonusWallet.getSportsBonusTransferDetails();
	}
	
	@Test(priority = 13)
	public void clickOnBettingPnLBtn() {
		tripleWallet.clickOnBettingPnL();
	}
	
	@Test(priority = 14)
	public void fetchBettingPnLDetails() throws InterruptedException {
		Thread.sleep(2000);
		tripleWallet.fetcgBettingPnLDetails();
	}

}
