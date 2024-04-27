package com.superwin.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.superwin.library.Utilities;
import com.superwin.library.gson_Model.GetGsonTestData;

public class EditProfilePositiveTestCase extends BaseTest{

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
		soft.assertAll();
	}
	
	@Test(priority = 3)
	public void clickOnProfileBtn() {
		loginpage.profileClick();
	}
	
	@Test(priority = 4)
	public void clickOnEditProfile() {
		editprofile.editProfile();
	}
	
	@Test(priority = 5)
	public void userInfoBeforeEdit() {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
	}

	@Test(priority = 6)
	public void verifyFirstOtp() throws Exception {
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		String actualMsg1 = editprofile.setOtp(otp);
		soft.assertEquals(actualMsg1, null);
		//soft.assertAll();
	}

	@Test(priority = 7)
	public void editUserInformation() throws InterruptedException {
		extentTest.info("Edit Profile Started");
		editprofile.editDOB("1991", "OCT", "17");
		Thread.sleep(1000);
		editprofile.editName("Testing");
		Thread.sleep(1000);
		editprofile.Gender("Male");
		Thread.sleep(1000);
		extentTest.info("Profile Edited Successfully");
	}

	@Test(priority = 8, dependsOnMethods = "editUserInformation")
	public void verifySecondOtp() throws Exception {
		String actualMsg = editprofile.update();
		soft.assertEquals(actualMsg, GetGsonTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		String actualMsg1 = editprofile.setSecondOtp(otp);
		soft.assertEquals(actualMsg1, GetGsonTestData.getConfigData().getProfileUpdate());
	//	soft.assertAll();
	}

	@Test(priority = 9, dependsOnMethods = "verifySecondOtp")
	public void userInfoAfterEdit() {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();		
	}
	
	@Test(priority = 10)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getKeyValueFromLocalStorage(driver, "accessTokenNewWallet");
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
