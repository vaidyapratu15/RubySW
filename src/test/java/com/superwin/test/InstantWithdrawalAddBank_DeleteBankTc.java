package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class InstantWithdrawalAddBank_DeleteBankTc extends BaseTest {

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
		Thread.sleep(2000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() throws InterruptedException {
		Thread.sleep(2000);
		loginpage.profileClick();
	}
	
	@Test(priority = 4)
	public void getWalletAmt() throws InterruptedException {
		withdrawalpage.getWalletDetails();
	}
	
	@Test(priority = 5)
	public void verifyWithdraw() throws InterruptedException {
		String actualData = instantwithdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
		Thread.sleep(2000);
	}
	
	@Test(priority = 6)
	public void verifyInstantWithdraw() throws InterruptedException {
		String actualData1 = instantwithdrawalpage.instantWithdraw();
		soft.assertEquals(actualData1, "InstantWithdraw Is Clickable..!!!");
		soft.assertAll();
	}

	@Test(priority = 7)
	public void verifyGetOtp() throws Exception {
		String actualData = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actualData, "AddNow Is Clickable..!!!");
		String actualData1 = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualData1, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = instantwithdrawalpage.getOtp();
		String actualData2 = instantwithdrawalpage.setOtp(otp);
		soft.assertEquals(actualData2, null);
	}

	@Test(priority = 8)
	public void verifyAddBankAccount() throws InterruptedException, FileNotFoundException {
		String accountno = instantwithdrawalpage.getAccountNumber();
		instantwithdrawalpage.setAccountNumber(accountno);
		instantwithdrawalpage.confirmAccountNumber(accountno);
		String ifsc = instantwithdrawalpage.getIfscCode();
		instantwithdrawalpage.setIfscCode(ifsc);
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		String actualData = instantwithdrawalpage.addAccount();
		soft.assertEquals(actualData, GetGsonTestData.getConfigData().getBankAdded());
	}

	@Test(priority = 9)
	public void getBankDetailBeforeDeleting() throws InterruptedException {
		instantwithdrawalpage.accountDetails();
	}
	
	@Test(priority = 10)
	public void verifyDeleteAccount() throws Throwable {
		instantwithdrawalpage.clickDeleteBtn();
		instantwithdrawalpage.deleteOtpPopUp();
		String otp = instantwithdrawalpage.getOtp();
		instantwithdrawalpage.setOtp(otp);
		instantwithdrawalpage.yes();
	}
	
	@Test(priority = 11)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 12)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
	}

}
