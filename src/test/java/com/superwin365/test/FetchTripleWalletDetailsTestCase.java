package com.superwin365.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class FetchTripleWalletDetailsTestCase extends BaseTest{

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
	}
	
	@Test(priority = 2)
	public void clickOnProfileBtn() {
		tripleWallet.clickOnProfileBtn();
	}
	
	@Test(priority = 3)
	public void fetchNormalWalletDetails() {
		String actualMsg = tripleWallet.getNormalWalletText();
		soft.assertEquals(actualMsg, "Able To Fetch Normal Wallet Text...!!!");
		
		String actualMsg1 = tripleWallet.getNormalWalletDetails();
		soft.assertEquals(actualMsg1, "Able To Fetch Wallet Amount and Net Exposure Amount...!!!");
		
		String actulMsg2 = tripleWallet.getNormalWalletAvailableWithdrawal();
		soft.assertEquals(actulMsg2, "Able To Fetch Available Withdrawal Balance...!!!");
		
		soft.assertAll();
	}
	
	@Test(priority = 4)
	public void fetchSportsWalletDetails() {
		String actualMsg = spWallet.getSportsWalletText();
		soft.assertEquals(actualMsg, "Able To Fetch The Sports Bonus Text...!!!");
		
		String actualMsg1 = spWallet.getSportsWalletDetails();
		soft.assertEquals(actualMsg1, "Able To Fetch The Sports Bonus Amount...!!!");
		
		String actualMsg2 = spWallet.getSportsNetExposureAmount();
		soft.assertEquals(actualMsg2, "Able To Fetch The Net Exposure Amount...!!!");
		
		soft.assertAll();
	}
	
	@Test(priority = 5)
	public void fetchBonusWalletDetails() {
		String actualMsg = bonusWallet.getBonusText();
		soft.assertEquals(actualMsg, "Able To Fetch The Bonus Text...!!!");
		
		String actualMsg1 = bonusWallet.getBonusWalletDetails();
		soft.assertEquals(actualMsg1, "Able To Fetch The Bonus Amount...!!!");
		
		String actualMsg2 = bonusWallet.getBonusNetExposureAmount();
		soft.assertEquals(actualMsg2, "Able To Fetch The Net Exposure Amount...!!!");
		
		soft.assertAll();
	}
	
	@Test(priority = 6)
	public void fetchAccessTokenNewWallet() {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + accessToken);
	}

	@Test(priority = 7)
	public void fetchDeviceId() throws FileNotFoundException, Exception {
		
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token After Login : " + accessToken);
		
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
	
}
