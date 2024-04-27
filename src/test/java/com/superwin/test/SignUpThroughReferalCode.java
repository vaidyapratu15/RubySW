package com.superwin.test;

import java.io.FileNotFoundException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class SignUpThroughReferalCode extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadTiming = (Long) js
				.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		// Print the page load timing
		extentTest.info("Page Load Time: " + loadTiming + " milliseconds");
	}

	@Test(priority = 0)
	public void clickOnRegisterBtn() throws InterruptedException {
		Thread.sleep(10000);
		signuppage.clickOnRegisterBtn();
		signuppage.scrollTillRegister();
	}

	@Test(priority = 1, dependsOnMethods = "clickOnRegisterBtn")
	public void verifyWhatsAppOnRegPage() {
		signuppage.checkWhatsApp();
		signuppage.checkWhatsAppIcon();
	}

	@Test(priority = 2)
	public void verifyTelegramOnRegPage() {
		telegramIcon.checkTelegramIconOnRegPage();
	}

	@Test(priority = 3)
	public void verifySignupWithStrongPwd() throws Exception {

		String name = signuppage.getName();
		signuppage.setName(name);

		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		String code = signuppage.getReferralCode();
		signuppage.setReferralCode(code);

		boolean btn = signuppage.clickRegister();
		org.testng.Assert.assertEquals(btn, true);

		String actualMsg = signuppage.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getUserRegistered());

		String query = signuppage.getQuery(number, "@fairplay.club");

		extentTest.info("Query : " + query);

		String otp = signuppage.setQuery(query);
		signuppage.setOtp(otp);

		boolean actualBtn2 = signuppage.clickConfirm();
		soft.assertEquals(actualBtn2, true);

		if (actualBtn2 == true) {
			GetGsonTestData.writeNewUserData(number, password, (number + "@fairplay.club"));
		}
	}

	@Test(priority = 4)
	public void verifySuccessfulSignUp() throws InterruptedException, FileNotFoundException {
		String actualMsg = signuppage.signupPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getSignUpSuccessufulMsg());
	}

	 @Test(priority = 5)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 6)
	public void verifyAcceptCookieBox() {
		Boolean actualBtn = signuppage.acceptCookie();
		soft.assertEquals(actualBtn, true);
	}

	@Test(priority = 7)
	public void verifyClickCheckBoxBox() {
		Boolean actualBtn = signuppage.clickCheckBox();
		soft.assertEquals(actualBtn, true);
	}

	@Test(priority = 8)
	public void verifyAcceptAndContinueBox() {
		Boolean actualBtn = signuppage.acceptAndContinue();
		soft.assertEquals(actualBtn, true);
	}

	@Test(priority = 9)
	public void verifyVisibilityOfDwnldAppPopup() {
		loginpage.downloadAppPopUp();
	}

	@Test(priority = 10)
	public void checkDeviceIdAfterLogin() throws FileNotFoundException, Exception {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");

		String deviceId = Utilities.getDeviceId(driver);
		extentTest.info("Device Id After Login : " + deviceId);
		soft.assertNotEquals(deviceId, null);

		//Thread.sleep(100000);

		String requestBody = "{\"deviceId\" : " + "\"" + deviceId + "\"}";

		String saveRealDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
				GetGsonTestData.getConfigData().getSaveRealDeviceId(), "accessToken", accessToken, requestBody);
		extentTest.info("SaveRealDeviceId Response : " + saveRealDeviceIdResponse);

		String accessToken1 = Utilities.getKeyValueFromLocalStorage(driver,"auth._token.customLocal");
		
		String saveDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
				GetGsonTestData.getConfigData().getSaveDeviceId(), "x-key-id",accessToken1, requestBody);
		extentTest.info("Save DeviceId Response : " + saveDeviceIdResponse);
	}

	@Test(priority = 11)
	public void verifyWalletDetails() {
		Boolean actualBtn = signuppage.profileClick();
		soft.assertEquals(actualBtn, true);

		signuppage.getWalletDetails();
	}
	
	@Test(priority = 12)
	public void verifyDepositPageBanner() throws InterruptedException {
		loginpage.profileClick();
		
		String actualMsg = deposit.depositNow();
		soft.assertEquals(actualMsg, "Deposite Now Is Clickable..!!!");
	}

	@Test(priority = 13)
	public void verifyDepositThroughUPI() throws InterruptedException {
		String actualtitle = deposit.depositThroughUPI("2000", "fdgsdcgsdfrwe");
		soft.assertEquals(actualtitle, "Invalid Request");
		soft.assertAll();
	}
	
	@Test(priority = 14)
	public void netBanking_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
	}
	
	@Test(priority = 15)
	public void clickOnAwaitingBonus() throws InterruptedException {
		loginpage.profileClick();
		turnover.clickOnAwaitingBonus();
	}
	
	@Test(priority = 16)
	public void verifyCheckBonusAmount() throws InterruptedException {
		turnover.countBonus();
		String expectedData = turnover.checkBonusAmt();
		soft.assertEquals(expectedData, "User Is Playing Game..!!!");
		soft.assertAll();
	}
	
	@Test(priority = 17, dependsOnMethods = "verifyCheckBonusAmount")
	public void getProfileInfoBeforePlaceBet() throws InterruptedException {
		extentTest.info("Before Bet Place");
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
		cricketPage.clickRandomly();
	}

	@Test(priority = 18, dependsOnMethods = "getProfileInfoBeforePlaceBet")
	public void verifyMatchesAvailable() {
		cricketPage.clickPlayexchCricketbtn();
		cricketPage.videoPopup();
		cricketPage.getPlayexchCricketEvents();
		cricketPage.setPlayexchMatch(driver);
		String text = cricketPage.getPlayexchCurrentlyMatches();
		extentTest.log(Status.INFO, text);
	}
	
	@Test(priority = 19, dependsOnMethods = "verifyMatchesAvailable")
	public void clickOnFirstMatch() throws InterruptedException {
	   fancyPage.clickOnFirstMatch();
	   Thread.sleep(10000);
	   fancyPage.searchFancyMatch();
	}
	
	@Test(priority = 20, dependsOnMethods = "clickOnFirstMatch")
	public void backFHorseBetPlace() throws InterruptedException {
		try { // First Event
			fancyPage.placeFBackBet();
			cricketPage.getPlayexchStakeValue();
			cricketPage.clickPlayexchPlaceBtn();
		} catch (Exception e) {
			extentTest.info("Unable to click on first event horse odds");
		}
	}
	
	@Test(priority = 21, dependsOnMethods = "backFHorseBetPlace")
	public void getProfileInfoAfterCompletingTurnover() throws InterruptedException {
		extentTest.info("After Bet Place");
		// getProfileInfo();
		Thread.sleep(20000);
		loginpage.profileClick();
		Thread.sleep(5000);
		withdrawalpage.getWalletDetails();
		
		turnover.clickOnAwaitingBonus();
		turnover.countBonus();
		
		loginpage.profileClick();
		Thread.sleep(5000);
		withdrawalpage.getWalletDetails();
	}
	
	@Test(priority = 22, dependsOnMethods = "getProfileInfoAfterCompletingTurnover" )
	public void verifyCheckConnectOnWhatsApp() {
		Boolean actualBtn1 = loginpage.checkConnectOnWhatsApp();
		soft.assertEquals(actualBtn1, true);
	}

	@Test(priority = 23, dependsOnMethods = "verifyCheckConnectOnWhatsApp")
	public void verifyWhatsApp() {
		Boolean actualBtn = loginpage.checkWhatsApp();
		soft.assertEquals(actualBtn, true);
	}

	@Test(priority = 24, dependsOnMethods = "verifyWhatsApp")
	public void verifyTelegram() {
		Boolean actualBtn = telegramIcon.checkTelegramIconOnProfileSection();
		soft.assertEquals(actualBtn, false);
	}

	@Test(priority = 25, dependsOnMethods = "verifyTelegram")
	public void verifyLogout() throws InterruptedException {
		Boolean actualBtn = signuppage.logout();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
	}

	@Test(priority = 26, dependsOnMethods = "verifyLogout")
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
	}

	@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testcasename = result.getMethod().getMethodName();
			Utilities.captureScreenshot(driver, testcasename + ".jpg");
		}
	}

}
