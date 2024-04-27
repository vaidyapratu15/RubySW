package com.superwin.sports.test;
//package com.fairplay.sports.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.superwin.library.gson_Model.GetGsonTestData;
import com.superwin.test.BaseTest;


public class FancyBetPlaceTestCases extends BaseTest {
	
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
	public void login() throws IOException {
		loginpage.setUsername(GetGsonTestData.getData().getUsername());

		loginpage.setPassword(GetGsonTestData.getData().getPassword());

////		loginpage.setUsername("7020125850");
////
////		loginpage.setPassword("Pass@123");

		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void getProfileInfoBeforePlaceBet() throws InterruptedException {
		extentTest.info("Before Bet Place");
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
		cricketPage.clickRandomly();
	}

	@Test(priority = 3)
	public void verifyMatchesAvailable() {
		cricketPage.clickPlayexchCricketbtn();
		cricketPage.videoPopup();
		cricketPage.getPlayexchCricketEvents();
		cricketPage.setPlayexchMatch(driver);
		String text = cricketPage.getPlayexchCurrentlyMatches();
		extentTest.log(Status.INFO, text);
	}
	
	@Test(priority = 4)
	public void clickOnFirstMatch() {
	   fancyPage.clickOnFirstMatch();
	   fancyPage.searchFancyMatch();
	}
	
	@Test(priority = 5, dependsOnMethods = "verifyMatchesAvailable")
	public void backFHorseBetPlace() throws InterruptedException {
		try { // First Event
			fancyPage.placeFBackBet();
			//cricketPage.getPlayexchStakeValue("100");
			cricketPage.clickPlayexchPlaceBtn();
		} catch (Exception e) {
			extentTest.info("Unable to click on first event horse odds");
		}
	}

}
