package com.superwin.test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class ResetPasswordPositiveTestCases extends BaseTest {

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
	public void verifyLogin() throws IOException {
		loginpage.setUsername(GetGsonTestData.getData().getUsername());
		loginpage.setPassword(GetGsonTestData.getData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		org.testng.Assert.assertEquals(actualbtn, true);

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
	public void verifyResetPwd() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonTestData.getData().getPassword());

		String newpassword = resetpage.getPwd();
		resetpage.setPwd(newpassword);
		
		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonTestData.getConfigData().getPwdChanged());

		Thread.sleep(1000);
		
		GetGsonTestData.writeJson(GetGsonTestData.getData().getUsername(), newpassword);

	}

	@Test(priority = 6)
	public void logout() {
		loginpage.downloadAppPopUp();
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 7)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver,"accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
