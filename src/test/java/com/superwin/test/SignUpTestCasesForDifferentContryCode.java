package com.superwin.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;

import java.io.FileNotFoundException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class SignUpTestCasesForDifferentContryCode extends BaseTest {

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
	
	@Test(priority = 0)
	public void verifyBaseURL() {
		signuppage.checkUrl();
		signuppage.scrollTillRegister();
	}

	@Test(priority = 1)
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

		boolean actualBtn = signuppage.clickRegister();
		soft.assertEquals(actualBtn, true);

		String actualMsg = signuppage.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getUserRegistered());

		String query = signuppage.getQueryAE(number, "@fairplay.club");

		extentTest.info("Query : " + query);

		String otp = signuppage.setQuery(query);
		signuppage.setOtp(otp);

		boolean actualBtn2 = signuppage.clickConfirm();
		soft.assertEquals(actualBtn2, true);
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
	public void verifyVisibilityOfDwnlAppPopup() {
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
		String actualMsg = deposit.depositNow();
		soft.assertEquals(actualMsg, "Deposite Now Is Clickable..!!!");
		
		Boolean actualBtn1 = deposit.checkVisibilityOfBanner();
		soft.assertEquals(actualBtn1, true);
		
		Thread.sleep(1000);
		
		int actualSize = deposit.sizeOfDeposit();
		soft.assertEquals(actualSize, 6);
	}

	@Test(priority = 13)
	public void verifyWithdrawalPageBanner() throws InterruptedException {
		Boolean actualBtn = signuppage.profileClick();
		soft.assertEquals(actualBtn, true);
		
		String actualData = withdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
		
		Thread.sleep(1000);
		
		Boolean actualBtn1 = withdrawalpage.checkVisibilityOfBanner();
		soft.assertEquals(actualBtn1, true);
	}
	
	@Test(priority = 14)
	public void verifyCheckConnectOnWhatsApp() {
		Boolean actualBtn = signuppage.profileClick();
		soft.assertEquals(actualBtn, true);
		
		Boolean actualBtn1 =loginpage.checkConnectOnWhatsApp();
		soft.assertEquals(actualBtn1, true);
	}
	
	@Test(priority = 15)
	public void verifyWhatsApp() {
		Boolean actualBtn = loginpage.checkWhatsApp();
		soft.assertEquals(actualBtn, true);
	}
	
	@Test(priority = 16)
	public void verifyTelegram() {
		Boolean actualBtn = telegramIcon.checkTelegramIconOnProfileSection();
		soft.assertEquals(actualBtn, false);
	}

	@Test(priority = 17)
	public void verifyLogout() throws InterruptedException {
		Boolean actualBtn = signuppage.logout();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
	}

	@Test(priority = 18)
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
