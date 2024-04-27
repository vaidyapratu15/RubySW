package com.superwin.test.affliliate;

import java.io.FileNotFoundException;

import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class UserReg extends BaseTest_registration2 {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void verifyBaseURL() {
		extentTest.info(driver.getCurrentUrl());
	}

	@Test(priority = 1)
	public void verifyWhatsAppOnRegPage() throws InterruptedException {
		aff.checkWhatsApp();
		aff.checkWhatsAppIcon();
	}

//	@Test(priority = 2)
	public void verifyTelegramOnRegPage() throws InterruptedException {
		telegramIcon.checkTelegramIconOnReg2Page();
		Thread.sleep(8000);
	}

	@Test(priority = 3)
	public void verifySignupWithStrongPwd() throws Exception {
		aff.setName();

		String number = aff.setNumber();

		String password = aff.setPassword();
		aff.setConfirmPassword(password);

		boolean btn = aff.clickRegister();
		org.testng.Assert.assertEquals(btn, true);

		String actualMsg = aff.popup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getUserRegistered());

		String requestBody = aff.getQuery(number, "@superwin.co");

		aff.verifyAffiliateCode(requestBody);
		extentTest.info("requestBody : " + requestBody);

		String otp = aff.getOTPFromDB(requestBody);
		aff.setOtp(otp);

		boolean actualBtn2 = aff.clickConfirm();
		soft.assertEquals(actualBtn2, true);
	}

	@Test(priority = 4)
	public void verifySuccessfulSignUp() throws InterruptedException, FileNotFoundException {
		String actualMsg = aff.signupPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getSignUpSuccessufulMsg());
	}
	
	@Test(priority = 5)
	public void checkAccessTokenAfterLogin() throws InterruptedException {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 6)
	public void verifyAcceptCookieBox() throws InterruptedException {
		String actualMsg = aff.acceptCookie();
		soft.assertEquals(actualMsg, "Accept cookie is clicked...!!!");
	}

	@Test(priority = 7)
	public void verifyClickCheckBoxBox() throws InterruptedException {
		String actualMsg = aff.clickCheckBox();
		soft.assertEquals(actualMsg, "CheckBox is clicked...!!!");
	}

	@Test(priority = 8)
	public void verifyAcceptAndContinueBox() throws InterruptedException {
		Boolean actualBtn = aff.acceptAndContinue();
		soft.assertEquals(actualBtn, true);
	}

	@Test(priority = 9)
	public void verifyVisibilityOfDwnlAppPopup() throws InterruptedException {
		loginpage.downloadAppPopUp();
	}

	@Test(priority = 10)
	public void checkDeviceIdAfterLogin() throws FileNotFoundException, Exception {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");

		String deviceId = Utilities.getKeyValueFromLocalStorage(driver, "device_id");
		extentTest.info("Device Id After Login : " + deviceId);
		soft.assertNotEquals(deviceId, null);

		String requestBody = "{\"deviceId\" : " + "\"" + deviceId + "\"}";

		String saveRealDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
				GetGsonTestData.getConfigData().getSaveRealDeviceId(), "accessToken", accessToken, requestBody);
		extentTest.info("SaveRealDeviceId Response : " + saveRealDeviceIdResponse);

		String accessToken1 = Utilities.getKeyValueFromLocalStorage(driver,"auth._token.customLocal");
		
		String saveDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
				GetGsonTestData.getConfigData().getSaveDeviceId(), "x-key-id",accessToken1, requestBody);
		extentTest.info("Save DeviceId Response : " + saveDeviceIdResponse);
	}
	
	@Test(priority = 12)
	public void verifyWalletDetails() throws InterruptedException {
		Boolean actualBtn = aff.profileClick();
		soft.assertEquals(actualBtn, true);
		
		aff.getWalletDetails();
	}

	//@Test(priority = 13)
	public void verifyWithdrawalPageBanner() throws InterruptedException {
//		Boolean actualBtn = signuppage.profileClick();
//		soft.assertEquals(actualBtn, true);

		String actualData = withdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");

		//Thread.sleep(2000);

		Boolean actualBtn1 = withdrawalpage.checkVisibilityOfBanner();
		soft.assertEquals(actualBtn1, true);
	}

	@Test(priority = 14)
	public void verifyCheckConnectOnWhatsApp() {
		Boolean actualBtn = aff.profileClick();
		soft.assertEquals(actualBtn, true);
		
		Boolean actualBtn1 =loginpage.checkConnectOnWhatsApp();
		soft.assertEquals(actualBtn1, true);
	}
	
	@Test(priority = 15)
	public void verifyWhatsApp() {
		Boolean actualBtn = loginpage.checkWhatsApp();
		soft.assertEquals(actualBtn, true);
	}
	
	//@Test(priority = 16)
	public void verifyTelegram() {
		Boolean actualBtn = telegramIcon.checkTelegramIconOnProfileSection();
		soft.assertEquals(actualBtn, false);
	}

	@Test(priority = 17)
	public void verifyLogout() throws InterruptedException {
		Boolean actualBtn1 = aff.logout();
		soft.assertEquals(actualBtn1, true);
	}

	@Test(priority = 18)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

	//@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testcasename = result.getMethod().getMethodName();
			Utilities.captureScreenshot(driver, testcasename + ".jpg");
		}
	}

}
