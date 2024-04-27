package com.superwin.test.affliliate;

import java.io.FileNotFoundException;

import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class AffiliateSignUpFlowThroughRegistration2 extends BaseTest_registration2 {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void verifyBaseURL() {
		signuppage.checkUrl();
		signuppage.affiliateCodeIdentification();
		signuppage.scrollTillRegister();
	}

	@Test(priority = 1)
	public void verifyWhatsAppOnRegPage() throws InterruptedException {
		signuppage.checkWhatsApp();
		signuppage.checkWhatsAppIcon();
		//Thread.sleep(5000);
	}

//	@Test(priority = 2)
	public void verifyTelegramOnRegPage() throws InterruptedException {
		telegramIcon.checkTelegramIconOnReg2Page();
		Thread.sleep(8000);
	}

	@Test(priority = 3)
	public void verifySignupWithStrongPwd() throws Exception {
		String name = signuppage.getName();
		signuppage.setName(name);

		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		String code = signuppage.getAffiliateCode("gfdshgfd");
		signuppage.checkAffiliateField(code);

//		boolean btn = signuppage.clickRegister();
//		org.testng.Assert.assertEquals(btn, true);
//
//		String actualMsg = signuppage.popup();
//		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getUserRegistered());
//
////		String query = signuppage.getQuery(number, "@fairplay.club");
//
//		String query = signuppage.getQuery(number, "@superwin.co");
//
//		extentTest.info("Query : " + query);
//
//		String otp = signuppage.setQuery(query);
//		signuppage.setOtp(otp);
//
//		boolean actualBtn2 = signuppage.clickConfirm();
//		soft.assertEquals(actualBtn2, true);
	}
	
//	@Test(priority = 4)
//	public void verifySuccessfulSignUp() throws InterruptedException, FileNotFoundException {
//		//Thread.sleep(2000);
//		String actualMsg = signuppage.signupPopup();
//		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getSignUpSuccessufulMsg());
//	}
//	
//	@Test(priority = 5)
//	public void checkAccessTokenAfterLogin() throws InterruptedException {
//		//Thread.sleep(2000);
//		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
//		extentTest.info("Access Token After Login : " + afterLogin);
//		soft.assertNotEquals(afterLogin, null);
//	}
//
//	@Test(priority = 6)
//	public void verifyAcceptCookieBox() throws InterruptedException {
//		//Thread.sleep(2000);
//		Boolean actualBtn = signuppage.acceptCookie();
//		soft.assertEquals(actualBtn, true);
//	}
//
//	@Test(priority = 7)
//	public void verifyClickCheckBoxBox() throws InterruptedException {
//		//Thread.sleep(2000);
//		Boolean actualBtn = signuppage.clickCheckBox();
//		soft.assertEquals(actualBtn, true);
//	}
//
//	@Test(priority = 8)
//	public void verifyAcceptAndContinueBox() throws InterruptedException {
//		//Thread.sleep(2000);
//		Boolean actualBtn = signuppage.acceptAndContinue();
//		soft.assertEquals(actualBtn, true);
//	}
//
//	@Test(priority = 9)
//	public void verifyVisibilityOfDwnlAppPopup() throws InterruptedException {
//		//Thread.sleep(2000);
//		loginpage.downloadAppPopUp();
//	}
//
//	@Test(priority = 10)
//	public void checkDeviceIdAfterLogin() throws FileNotFoundException, Exception {
//		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
//
//		String deviceId = Utilities.getDeviceId(driver);
//		extentTest.info("Device Id After Login : " + deviceId);
//		soft.assertNotEquals(deviceId, null);
//
//		//Thread.sleep(100000);
//
//		String requestBody = "{\"deviceId\" : " + "\"" + deviceId + "\"}";
//
//		String saveRealDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
//				GetGsonTestData.getConfigData().getSaveRealDeviceId(), "accessToken", accessToken, requestBody);
//		extentTest.info("SaveRealDeviceId Response : " + saveRealDeviceIdResponse);
//
//		String accessToken1 = Utilities.getKeyValueFromLocalStorage(driver,"auth._token.customLocal");
//		
//		String saveDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
//				GetGsonTestData.getConfigData().getSaveDeviceId(), "x-key-id",accessToken1, requestBody);
//		extentTest.info("Save DeviceId Response : " + saveDeviceIdResponse);
//	}
//	
//	@Test(priority = 11)
//	public void verifyDepositPageBanner() throws InterruptedException {
//		String actualMsg = deposit.depositNow();
//		soft.assertEquals(actualMsg, "Deposite Now Is Clickable..!!!");
//
//		Boolean actualBtn1 = deposit.checkVisibilityOfBanner();
//		soft.assertEquals(actualBtn1, true);
//
//		//Thread.sleep(2000);
////
////		int actualSize = deposit.sizeOfDeposit();
////		soft.assertEquals(actualSize, 6);
//	}
//	
//	@Test(priority = 12)
//	public void verifyWalletDetails() throws InterruptedException {
//		//Thread.sleep(2000);
//		Boolean actualBtn = signuppage.profileClick();
//		soft.assertEquals(actualBtn, true);
//		//Thread.sleep(2000);
//		signuppage.getWalletDetails();
//	}
//
//	@Test(priority = 13)
//	public void verifyWithdrawalPageBanner() throws InterruptedException {
////		Boolean actualBtn = signuppage.profileClick();
////		soft.assertEquals(actualBtn, true);
//
//		String actualData = withdrawalpage.withdrawNow();
//		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
//
//		//Thread.sleep(2000);
//
//		Boolean actualBtn1 = withdrawalpage.checkVisibilityOfBanner();
//		soft.assertEquals(actualBtn1, true);
//	}
//
//	@Test(priority = 14)
//	public void verifyCheckConnectOnWhatsApp() {
//		Boolean actualBtn = signuppage.profileClick();
//		soft.assertEquals(actualBtn, true);
//		
//		Boolean actualBtn1 =loginpage.checkConnectOnWhatsApp();
//		soft.assertEquals(actualBtn1, true);
//	}
//	
//	@Test(priority = 15)
//	public void verifyWhatsApp() {
//		Boolean actualBtn = loginpage.checkWhatsApp();
//		soft.assertEquals(actualBtn, true);
//	}
//	
//	@Test(priority = 16)
//	public void verifyTelegram() {
//		Boolean actualBtn = telegramIcon.checkTelegramIconOnProfileSection();
//		soft.assertEquals(actualBtn, false);
//	}
//
//	@Test(priority = 17)
//	public void verifyLogout() throws InterruptedException {
//		Boolean actualBtn = signuppage.logout();
//		soft.assertEquals(actualBtn, true);
//		//Thread.sleep(1000);
//	}
//
//	@Test(priority = 18)
//	public void checkAccessTokenAfterLogout() {
//		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
//		extentTest.info("Access Token After Logout : " + afterlogout);
//		soft.assertEquals(afterlogout, null);
//		soft.assertAll();
//	}
//
//	//@AfterMethod
//	public void onTestFailure(ITestResult result) throws InterruptedException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			String testcasename = result.getMethod().getMethodName();
//			Utilities.captureScreenshot(driver, testcasename + ".jpg");
//		}
//	}
}
	