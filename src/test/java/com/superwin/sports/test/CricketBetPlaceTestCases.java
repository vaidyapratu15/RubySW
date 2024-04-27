//package com.superwin.sports.test;
////package com.fairplay.sports.test;
////
//import java.io.IOException;
//import org.openqa.selenium.JavascriptExecutor;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//import com.aventstack.extentreports.Status;
//import com.fairplay.library.Utilities;
//import com.fairplay.library.gson_Model.GetGsonTestData;
//import com.fairplay.test.BaseTest;
//
//public class CricketBetPlaceTestCases extends BaseTest {
//
//	SoftAssert soft = new SoftAssert();
//
//	@Test(priority = 0)
//	public void getBrowserLink() throws InterruptedException {
//		extentTest.info("Link : " + driver.getCurrentUrl());
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		long loadTiming = (Long) js
//				.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
//		// Print the page load timing
//		extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
//		driver.manage().timeouts().getScriptTimeout();
//	}
//
//	@Test(priority = 1)
//	public void login() throws IOException, InterruptedException {
//		Thread.sleep(10000);
//		loginpage.setUsername(GetGsonTestData.getData().getUsername());
//
//		loginpage.setPassword(GetGsonTestData.getData().getPassword());
//
////		loginpage.setUsername("7020125850");
////
////		loginpage.setPassword("Pass@123");
//
//		Boolean actualBtn = loginpage.login();
//		soft.assertEquals(actualBtn, true);
//
//		loginpage.downloadAppPopUp();
//
//		loginpage.walletAmount(driver);
//	}
//
//	@Test(priority = 2)
//	public void checkAccessTokenAfterLogin() {
//		String afterLogin = Utilities.getAccessToken(driver);
//		extentTest.info("Access Token Before Logout : " + afterLogin);
//		soft.assertNotEquals(afterLogin, null);
//	}
//
//	@Test(priority = 3)
//	public void getProfileInfoBeforePlaceBet() throws InterruptedException {
//		extentTest.info("Before Bet Place");
//		loginpage.profileClick();
//		withdrawalpage.getWalletDetails();
//		cricketPage.clickRandomly();
//	}
//
//	@Test(priority = 4)
//	public void verifyMatchesAvailable() {
//		cricketPage.clickPlayexchCricketbtn();
//		cricketPage.videoPopup();
//		cricketPage.getPlayexchCricketEvents();
//		cricketPage.setPlayexchMatch(driver);
//		String text = cricketPage.getPlayexchCurrentlyMatches();
//		extentTest.log(Status.INFO, text);
//	}
//
//	@Test(priority = 5, dependsOnMethods = "verifyMatchesAvailable")
//	public void backFHorseBetPlace() throws InterruptedException {
//		cricketPage.FirstMatchName();
//		try { // First Event
//			cricketPage.clickPlayexchFHorseBack();
//			cricketPage.getPlayexchStakeValue("100");
//			cricketPage.clickPlayexchPlaceBtn();
//		} catch (Exception e) {
//			extentTest.info("Unable to click on first event horse odds");
//		}
//	}
//	
//	@Test(priority = 6, dependsOnMethods = "backFHorseBetPlace")
//	public void getProfileInfoAfterbackFHorseBetPlace() throws InterruptedException {
//		extentTest.info("After Bet Place");
//		// getProfileInfo();
//		loginpage.profileClick();
//		Thread.sleep(5000);
//		withdrawalpage.getWalletDetails();
//		cricketPage.clickRandomly();
//	}
//	
//	@Test(priority = 7, dependsOnMethods = "verifyMatchesAvailable")
//	public void layFHorseBetPlace() throws InterruptedException {
//		cricketPage.FirstMatchName();
//		try { // First Event
//			cricketPage.clickPlayexchFHorseLay();
//			cricketPage.getPlayexchStakeValue("100");
//			cricketPage.clickPlayexchPlaceBtn();
//		} catch (Exception e) {
//			extentTest.info("Unable to click on first event horse odds");
//		}
//	}
//	
//	@Test(priority = 8, dependsOnMethods = "layFHorseBetPlace")
//	public void getProfileInfoAfterlayFHorseBetPlace() throws InterruptedException {
//		extentTest.info("After Bet Place");
//		// getProfileInfo();
//		loginpage.profileClick();
//		Thread.sleep(5000);
//		withdrawalpage.getWalletDetails();
//		cricketPage.clickRandomly();
//	}
//	
//	
//	@Test(priority = 9, dependsOnMethods = "verifyMatchesAvailable")
//	public void backSHorseBetPlace() throws InterruptedException {
//		cricketPage.FirstMatchName();
//		try { // First Event
//			cricketPage.clickPlayexchSHorseBack();
//			cricketPage.getPlayexchStakeValue("100");
//			cricketPage.clickPlayexchPlaceBtn();
//		} catch (Exception e) {
//			extentTest.info("Unable to click on first event horse odds");
//		}
//	}
//	
//	@Test(priority = 10, dependsOnMethods = "backSHorseBetPlace")
//	public void getProfileInfoAfterbackSHorseBetPlace() throws InterruptedException {
//		extentTest.info("After Bet Place");
//		loginpage.profileClick();
//		Thread.sleep(5000);
//		withdrawalpage.getWalletDetails();
//		cricketPage.clickRandomly();
//	}
//	
//	@Test(priority = 11, dependsOnMethods = "verifyMatchesAvailable")
//	public void laySHorseBetPlace() throws InterruptedException {
//		cricketPage.FirstMatchName();
//		try { // First Event
//			cricketPage.clickPlayexchSHorseLay();
//			cricketPage.getPlayexchStakeValue("100");
//			cricketPage.clickPlayexchPlaceBtn();
//		} catch (Exception e) {
//			extentTest.info("Unable to click on first event horse odds");
//		}
//	}
//	
//	@Test(priority = 12, dependsOnMethods = "laySHorseBetPlace")
//	public void getProfileInfoAfterlaySHorseBetPlace() throws InterruptedException {
//		extentTest.info("After Bet Place");
//		loginpage.profileClick();
//		Thread.sleep(5000);
//		withdrawalpage.getWalletDetails();
//		cricketPage.clickRandomly();
//	}
//	
//}
