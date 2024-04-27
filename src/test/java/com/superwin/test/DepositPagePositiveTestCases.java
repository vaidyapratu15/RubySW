package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.superwin.library.OtpExtractor;
import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class DepositPagePositiveTestCases extends BaseTest {
	
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
	public void login() throws IOException, InterruptedException {
		Thread.sleep(1000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());
			
		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		//loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}
	
	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
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
	public void verifyDeposit() throws InterruptedException {
		deposit.checkWalletAmount();
		deposit.depositNow();
	}
	
	//@Test(priority = 6)
//	public void verifydepositThroughFPBook() throws InterruptedException {
//		String actualtitle = deposit.depositThroughFPBook();
//		soft.assertEquals(actualtitle, "Share on WhatsApp");
//	}

	@Test(priority = 7)
	public void verifyDepositThroughUPI() throws InterruptedException {
		String actualtitle = deposit.depositThroughUPI("500", "fdgsdcgsdfrwe");
		soft.assertEquals(actualtitle, "Invalid Request");
	}
	
	@Test(priority = 8)
	public void upi_transaction_details() throws InterruptedException {
		deposit.checkTransactions();
		Thread.sleep(1000);
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

	@Test(priority = 9)
	public void verifyDepositThroughNetBanking() throws InterruptedException {
		String actualtitle2 = deposit.depositThroughNetBanking("601", "TEJ1234");
		soft.assertEquals(actualtitle2, "Cashier | fairplay.club INR");
	}
	
	@Test(priority = 10)
	public void netBanking_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}
	
////	@Test(priority = 11)
////	public void verifyDepositThroughBankDeposit() throws InterruptedException, IOException, AWTException {
////		deposit.depositThroughBankDeposit("701");
////	}
//	
////	@Test(priority = 12)
//	public void bankDepposit_transaction_details() {
//		deposit.getTransactionDetails();
//		loginpage.profileClick();
//		deposit.depositNow();
//	}
//
	@Test(priority = 13)
	public void verifyDepositThroughAstropay() throws InterruptedException {
		String actualtitle4 = deposit.depositThroughAstropay("800", "876543567iuyghfdsx");
		soft.assertEquals(actualtitle4, "AstroPay OneTouch - Deposit");	
	}
	
	@Test(priority = 14)
	public void astropay_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

	@Test(priority = 15)
	public void verifydepositThroughCrypto() throws InterruptedException {
		String actualtitle5 = deposit.depositThroughCrypto();
		soft.assertEquals(actualtitle5, "Select Currency - iPint");	
	}
	
	@Test(priority = 16)
	public void crypto_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
	}
//
////	@AfterMethod
////	public void onTestFailure(ITestResult result) throws InterruptedException {
////		if (result.getStatus() == ITestResult.FAILURE) {
////			String testcasename = result.getMethod().getMethodName();
////			Utilities.captureScreenshot(driver, testcasename + ".jpg");
////		}
////	}
//	

}
