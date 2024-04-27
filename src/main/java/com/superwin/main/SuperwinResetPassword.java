package com.superwin.main;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.RandomizeData;

public class SuperwinResetPassword extends BaseClass {

	ActionClass act = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();

	@FindBy(xpath = "//div[contains(text(),'Reset Password')]")
	WebElement resetPwd;

	@FindBy(xpath = "(//input[@type = 'password'])[1]")
	WebElement oldPwd;

	@FindBy(xpath = "(//input[@type = 'password'])[2]")
	WebElement newPwd;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[3]")
	WebElement newPwdErrMsg;

	@FindBy(xpath = "(//input[@type = 'password'])[3]")
	WebElement confirmNewPwd;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[4]")
	WebElement confirmNewPwdErrMsg;

	@FindBy(xpath = "//span[contains(text(),'Reset Password')]")
	WebElement clickReset;

	@FindBy(xpath = ("//i[@class='v-icon notranslate mdi mdi-reload theme--dark white--text']"))
	WebElement refresh;

	@FindBy(xpath = ("//div//div[@role='status']"))
	WebElement popup;

	@SuppressWarnings("static-access")
	public SuperwinResetPassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public String getPwd() {
		String new_pwd = rand.randomizeStrongPassword();
		return new_pwd;
	}

	public void setPwd(String new_pwd) throws InterruptedException {
		//this.new_pwd = new_pwd;
		setNewPwd(new_pwd);
		Thread.sleep(1000);
		setConfirmNewPwd(new_pwd);
	}
	
	public void ResetPwd() {
		try {
			act.jSForScroll(driver, resetPwd);
			resetPwd.click();
			extentTest.info("Reset Password is clicked");
		} catch (Exception e) {
			extentTest.info("Reset Password is not clickable");
		}
	}

	public void oldPwd(String oldPass) throws IOException {
		oldPwd.sendKeys(oldPass);
		extentTest.info("Old Password : " + oldPass);
	}

	public void setNewPwd(String newPass) {
		newPwd.sendKeys(newPass);
		extentTest.info("New Password Is : "+ newPass);
		try {
			ewait.visibilityOf(driver, newPwdErrMsg, 2);
			String Msg = newPwdErrMsg.getText();
			extentTest.info("New Password Error Message : " + Msg);
		} catch (Exception e) {
			extentTest.info("New Password Error Message : New Password is valid");
		}
	}

	public void setConfirmNewPwd(String confirmPwd) {
		confirmNewPwd.sendKeys(confirmPwd);
		extentTest.info("Confirm Password Is : "+ confirmPwd);
		try {
			ewait.visibilityOf(driver, confirmNewPwdErrMsg, 2);
			String Msg = confirmNewPwdErrMsg.getText();
			extentTest.info("Confirm Password Error Message : " + Msg);
		} catch (Exception e) {
			extentTest.info("Confirm Password Error Message : Confirm Password is valid");
		}
	}

	public String resetPassword() {
		try {
			ewait.elementToBeClickable(driver, clickReset, 2);
			clickReset.click();
			Thread.sleep(1000);
			String msg = popup(driver);
			return msg;
		} catch (Exception e) {
			refresh.click();
			extentTest.info("Verification Message :  Reset button is disabled");
			return "Reset button is disabled";
		}
	}

	public String popup(WebDriver driver) {
		ewait.visibilityOf(driver, popup, 2);
		String msg = popup.getText();
		extentTest.info("Verification Message : " + msg);
		if (!(msg.equals("Password changed successfully"))) {
			ewait.invisibilityOf(driver, popup, 2);
			refresh.click();
			return msg;
		} else {
			return msg;
		}
	}

	public void refresh() throws InterruptedException {
		refresh.click();
		Thread.sleep(2000);
	}

	

}
