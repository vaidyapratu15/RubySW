package com.superwin.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class ReferralFlowTestCases extends BaseTest{
	
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
	
	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token Before Logout : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}
	
	//@Test(priority = 3)
	public void checkDeviceIdAfterLogin() throws FileNotFoundException, Exception {
		String accessToken = Utilities.getKeyValueFromLocalStorage(driver,"auth._token.customLocal");
		extentTest.info("Access Token After Login : " + accessToken);
		soft.assertNotEquals(accessToken, null);

		String deviceId = Utilities.getDeviceId(driver);
		extentTest.info("Device Id After Login : " + deviceId);
		soft.assertNotEquals(deviceId, null);

		//Thread.sleep(100000);

		String requestBody = "{\"deviceId\" : " + "\"" + deviceId + "\"}";
		
		String saveDeviceIdResponse = OtpExtractor.sendPostRequestForDeviceId(
				GetGsonTestData.getConfigData().getSaveDeviceId(), "x-key-id", accessToken, requestBody);
		extentTest.info("Save DeviceId Response : " + saveDeviceIdResponse);
		
	}
	
	@Test(priority = 4)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}
	
	@Test(priority = 5)
	public void getReferralCode() {
		referAndEarn.clickOnReferAndEarn();
		referAndEarn.copyReferalCode();
	}
	
	@Test(priority = 6)
	public void getReferredUsers() {
		referAndEarn.getReferredUserDetails();
	}
	
	@Test(priority = 7)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 8)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();
	}


}
