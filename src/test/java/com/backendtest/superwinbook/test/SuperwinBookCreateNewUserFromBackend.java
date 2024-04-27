package com.backendtest.superwinbook.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.superwin.library.gson_Model.GetGsonSportsTestData;

public class SuperwinBookCreateNewUserFromBackend extends SuperwinBook_BaseTest{
	
	@Test
	public void verifyLogin() throws FileNotFoundException {
		extentTest.info(driver.getCurrentUrl());
		
		String userId = loginpage.getUsername();
		loginpage.setUsername(userId);
		
		String pwd = loginpage.getPassword();
		loginpage.setPassword(pwd);
		
		loginpage.signIn();
	}
	
	@Test(priority = 1)
	public void verifyCreateNewUser() throws InterruptedException, IOException {
		createNewUser.createNewUser();
		
		String userId = createNewUser.getUsername();
		createNewUser.setUsername(userId);
		
		String mobNo = createNewUser.getMobileNo();
		createNewUser.setMobileNo(mobNo);
		
		String pwd = createNewUser.getPassword();
		createNewUser.setPassword(pwd);
		
		createNewUser.setConfirmPassword(pwd);
		
		createNewUser.save();
		
		GetGsonSportsTestData.writeJson(userId, pwd, mobNo);
		
		createNewUser.searchNewCreatedUser(userId);
	}
	
	@Test(priority = 2)
	public void verifyLogout() throws InterruptedException {
		loginpage.clickonLeftSlider();
		Thread.sleep(1000);
		loginpage.logout();
	}

}
