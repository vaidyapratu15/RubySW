package com.superwin.admin.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class AdminPanelApproveDepositTestCases extends Admin_BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 0)
	public void verifyLoginPositiveTc() throws Exception {
		String username = adminlogin.getUsername();
		adminlogin.setUsername(username);

		adminlogin.clickNext();

		Thread.sleep(10000);
		
		String otp = adminlogin.getOtp();
		adminlogin.setOtp(otp);

		String password = adminlogin.getPassword();
		adminlogin.setPassword(password);

		adminlogin.login();
		
		Thread.sleep(10000);
	}

	@Test(priority = 1)
	public void checkAccessTokenAfterLogin() throws IOException, InterruptedException {
		String beforelogout = Utilities.getKeyValueFromLocalStorage(driver,"accessToken");
		extentTest.info("Access Token Before Logout : " + beforelogout);
		soft.assertNotEquals(beforelogout, null);
		Thread.sleep(10000);
		GetGsonTestData.writeAccessToken(beforelogout);
	}
	
	@Test(priority = 2)
	public void verifyClickOnTransactionsBtn() {
		approveDeposit.clickOnTransactionsBtn();
	}
	
	@Test(priority = 3)
	public void verifyCheckTransactions() {
		approveDeposit.checkTransactions();
	}
	
	@Test(priority = 4)
	public void verifySearchByEmail() {
		approveDeposit.searchByEmail();
		approveDeposit.searchDepositTransactions();
	}
	
	@Test(priority = 5)
	public void verifyApproveDepositRequest() throws InterruptedException, FileNotFoundException {
		Thread.sleep(1000);
		approveDeposit.approveDepositRequest();
		soft.assertAll();
	}
	
}
