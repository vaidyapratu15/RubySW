package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class ResetPasswordNegativeTestCases extends BaseTest {

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
	public void verifyLogin() throws IOException, InterruptedException {
		Thread.sleep(2000);
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.loginPopup();
		
		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void verifyProfileClick() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyResetPwdBtn() {
		resetpage.ResetPwd();
	}

	@Test(priority = 5)
	public void verifyResetPwd_Unmatched() throws IOException {
		resetpage.oldPwd(GetGsonTestData.getData().getPassword());

		String newpassword = GetGsonTestData.getConfigData().getNewPwdUnmatched();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getConfirmPwdUnmatched();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, "Reset button is disabled");
	}

	@Test(priority = 6)
	public void verifyResetPwd_ContainsSpace() throws IOException {

		resetpage.oldPwd(GetGsonTestData.getData().getPassword());

		String newpassword = GetGsonTestData.getConfigData().getPwdContainsSpace();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getPwdContainsSpace();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, "Reset button is disabled");
	}

	@Test(priority = 7)
	public void verifyResetPwd_Uppercase() throws IOException, InterruptedException {
		resetpage.oldPwd(GetGsonTestData.getUserData().getPassword());

		String newpassword = GetGsonTestData.getConfigData().getPwdUppercase();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getPwdUppercase();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getWeakPwd());
		Thread.sleep(50000);

	}

	@Test(priority = 8)
	public void verifyResetPwd_Lowercase() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonTestData.getUserData().getPassword());

		String newpassword = GetGsonTestData.getConfigData().getPwdLowercase();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getPwdLowercase();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getWeakPwd());
		Thread.sleep(50000);
	}

	@Test(priority = 9)
	public void verifyResetPwd_Numbers() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonTestData.getUserData().getPassword());

		String newpassword = GetGsonTestData.getConfigData().getPwdNumbers();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getPwdNumbers();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getWeakPwd());
		Thread.sleep(50000);

	}

	@Test(priority = 10)
	public void verifyResetPwd_OldPassword() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonTestData.getConfigData().getPwdOldPassword());

		String newpassword = GetGsonTestData.getConfigData().getNewPassword();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getConfigData().getNewPassword();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getUnmatchedOldPwd());
		Thread.sleep(50000);

	}

	@Test(priority = 11)
	public void verifyResetPwd_OldNewSame() throws IOException, InterruptedException {
		resetpage.oldPwd(GetGsonTestData.getData().getPassword());

		String newpassword = GetGsonTestData.getData().getPassword();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonTestData.getData().getPassword();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getUnmatchedPwd());
		
		//Thread.sleep(50000);
	}

	@Test(priority = 12)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 13)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
