package com.superwin.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class TurnOverTestCases extends BaseTest{

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 0)
	public void getBrowserLink() throws InterruptedException {
		extentTest.info("Link : " + driver.getCurrentUrl());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadTiming = (Long) js
				.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		// Print the page load timing
		extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
		driver.manage().timeouts().getScriptTimeout();
	}

	@Test(priority = 1)
	public void login() throws IOException, InterruptedException {
		Thread.sleep(10000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());

		loginpage.setPassword(GetGsonTestData.getData().getPassword());

//		loginpage.setUsername("9898038920");
//
//		loginpage.setPassword("Pass123");

		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token Before Logout : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}
	
	@Test(priority = 4)
	public void clickOnAwaitingBonus() throws InterruptedException {
		turnover.clickOnAwaitingBonus();
	}
	
	@Test(priority = 5)
	public void verifyCheckBonusAmount() throws InterruptedException {
		turnover.countBonus();
		String expectedData = turnover.checkBonusAmt();
		soft.assertEquals(expectedData, "User Is Playing Game..!!!");
		soft.assertAll();
	}
	
	@Test(priority = 6, dependsOnMethods = "verifyCheckBonusAmount")
	public void getProfileInfoBeforePlaceBet() throws InterruptedException {
		extentTest.info("Before Bet Place");
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
		cricketPage.clickRandomly();
	}

	@Test(priority = 7, dependsOnMethods = "getProfileInfoBeforePlaceBet")
	public void verifyMatchesAvailable() {
		cricketPage.clickPlayexchCricketbtn();
		cricketPage.videoPopup();
		cricketPage.getPlayexchCricketEvents();
		cricketPage.setPlayexchMatch(driver);
		String text = cricketPage.getPlayexchCurrentlyMatches();
		extentTest.log(Status.INFO, text);
	}
	
	@Test(priority = 8, dependsOnMethods = "verifyMatchesAvailable")
	public void clickOnFirstMatch() throws InterruptedException {
	   fancyPage.clickOnFirstMatch();
	   Thread.sleep(10000);
	   fancyPage.searchFancyMatch();
	}
	
	@Test(priority = 9, dependsOnMethods = "clickOnFirstMatch")
	public void backFHorseBetPlace() throws InterruptedException {
		try { // First Event
			fancyPage.placeFBackBet();
			cricketPage.getPlayexchStakeValue();
			cricketPage.clickPlayexchPlaceBtn();
		} catch (Exception e) {
			extentTest.info("Unable to click on first event horse odds");
		}
	}
	
	@Test(priority = 10, dependsOnMethods = "backFHorseBetPlace")
	public void getProfileInfoAfterbackFHorseBetPlace() throws InterruptedException {
		extentTest.info("After Bet Place");
		// getProfileInfo();
		Thread.sleep(20000);
		loginpage.profileClick();
		Thread.sleep(5000);
		withdrawalpage.getWalletDetails();
		
		turnover.clickOnAwaitingBonus();
		turnover.countBonus();
		
		Thread.sleep(10000);
		loginpage.profileClick();
		Thread.sleep(5000);
		withdrawalpage.getWalletDetails();
	}
}
