package com.superwin365.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TurnONSportsWallet extends BaseTest {

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
	public void login() throws InterruptedException {
		Thread.sleep(2000);
		loginpage.setUsername("9975105804");
		loginpage.setPassword("Pass@123");
		
//		loginpage.setUsername("9970041190");
//		loginpage.setPassword("Pass@123");

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
	public void turnONSportsWallet() throws InterruptedException {
		String actualMsg = spWallet.turnONSportsWallet();
		soft.assertEquals(actualMsg, "Sports Bonus Wallet Turned ON...!!!");

		soft.assertAll();

		Thread.sleep(30000);
	}

	@Test(priority = 4)
	public void fetchWalletDetails() {
		tripleWallet.getWalletAmt();

		String actualMsg = tripleWallet.clickOnProfileBtn();
		soft.assertEquals(actualMsg, "Profile Button Is Clickable...!!!");

		String actualMsg1 = spWallet.getSportsWalletText();
		soft.assertEquals(actualMsg1, "Able To Fetch The Sports Bonus Text...!!!");

		String actualMsg2 = spWallet.getSportsWalletDetails();
		soft.assertEquals(actualMsg2, "Able To Fetch The Sports Bonus Amount...!!!");

		String actualMsg3 = spWallet.getSportsNetExposureAmount();
		soft.assertEquals(actualMsg3, "Able To Fetch The Net Exposure Amount...!!!");

		soft.assertAll();
	}

	@Test(priority = 5)
	public void clickOnSportsBonusInfoBtn() {
		String actualMsg = spWallet.clickOnSportsBonusInfoBtn();
		soft.assertEquals(actualMsg, "Able To Click On Sports Bonus Info Button...!!!");

		soft.assertAll();
	}

	@Test(priority = 6)
	public void checkVisibilityOfSportsBonusInfoBonus() {
		Boolean data = spWallet.visibilityOfSportsBonusInfoBonus();
		soft.assertEquals(data, true);

		soft.assertAll();
	}

	@Test(priority = 7)
	public void fetchSportsBonusInfo() {
		spWallet.fetchSportsBonusInfoTitle();
		String actualTitle1 = spWallet.fetchSportsBonusInfo();
		soft.assertEquals(actualTitle1, "Able To Fetch The Bonus Info...!!!");

		soft.assertAll();
	}

	@Test(priority = 8)
	public void clickOnSportsBonusWalletDropdown() throws InterruptedException {
		spWallet.clickOnSportsBonusWalletDropdown();
	}
	
	@Test(priority = 9)
	public void fetchSportsBonusWalletDetails() {
		spWallet.getSportsBonusDetails();
	}
	
	@Test(priority = 10)
	public void clickOnTermsAndConditionDetails() {
		spWallet.clickOnTermsAndConditions();
	}
	
	@Test(priority = 11)
	public void clickOnViewMoreBtn() {
		spWallet.clickOnViewMore();
	}
	
	@Test(priority = 12)
	public void fetchPendingSportsBonusTransferDetails() {
		spWallet.getSportsBonusTransferDetails();
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
