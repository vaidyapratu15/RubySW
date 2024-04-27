package com.superwin.main;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.OtpExtractor;
import com.superwin.library.RandomizeData;
import com.superwin.library.gson_Model.GetGsonTestData;

public class SuperwinForgotPassword extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//div[@class = 'text-right caption mt-n2 mr-2 error_message']//a[contains(text(),'Forgot Password?')]")
	WebElement forgotPasswordLandingPage;

	@FindBy(xpath = "//div[@class = 'row mt-2']//span[contains(text(),'Forgot Password ?')]")
	WebElement forgotPasswordLoginPage;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[1]//input")
	WebElement enterNumber;

	@FindBy(xpath = "(//div[@class='v-select__selections'])[1]")
	WebElement selCountryCode;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div")
	List<WebElement> listCountrCode;

	@FindBy(xpath = "(//button//span[normalize-space()='Forgot Password'])[1]")
	WebElement forgotPasswordButton;

	@FindBy(xpath = "//div//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[2]//input")
	WebElement enterOtp;

	@FindBy(xpath = "//div[@class='v-messages__message message-transition-enter-to']")
	WebElement errorMsgForOtp;

	//// div[@class='v-messages__message message-transition-enter-to']

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[3]//input")
	WebElement newPassword;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[3]")
	WebElement errorNewPassword;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[4]//input")
	WebElement confirmPassword;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[4]")
	WebElement errorConfirmPassword;

	@FindBy(xpath = "(//form//button[@type='button'])[3]")
	WebElement changePassword;

	@FindBy(xpath = "//span[contains(text(),'Resend Code')]")
	WebElement resendOtp;

	@FindBy(xpath = "//span[@class = 'sms-not-valid']")
	WebElement errorMsg;

	@SuppressWarnings("static-access")
	public SuperwinForgotPassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	private String username;
//	private String otp;
//	private String pwd;

	public void forgotPassword() {
		try {
			ewait.visibilityOf(driver, forgotPasswordLandingPage, 6);
			ewait.elementToBeClickable(driver, forgotPasswordLandingPage, 6);
			forgotPasswordLandingPage.click();
		} catch (Exception e) {
			extentTest.info("Forgot Password Is Not Clickable...!!!");
		}
	}

	public void forgotPasswordLoginPage() {
		try {
			forgotPasswordLoginPage.click();
		} catch (Exception e) {
			extentTest.info("Forgot Password On loginPage Is Not Clickable...!!!");
		}
	}

	public void contryCode(String code) {
		selCountryCode.click();
		for (WebElement countrycode : listCountrCode) {
			String codetext = countrycode.getText();
			if (codetext.equals(code)) {
				extentTest.info("Country code is : " + countrycode.getText());
				countrycode.click();
				ewait.elementToBeClickable(driver, countrycode, 2);
			}
		}
	}

	public String getUsernumber() throws IOException {
		String username = GetGsonTestData.getData().getUsername();
		return username;
	}

	public void setUsernumber(String usernumber) {
		//this.username = usernumber;
		try {
			enterNumber.sendKeys(usernumber);
			extentTest.info("Mobile Number : " + usernumber);
		} catch (Exception e) {
			extentTest.info("Not Able To Enter UserNumber..!!!");
		}
	}

	public void forgotPasswordButton() {
		try {
			//ewait.visibilityOf(driver, forgotPasswordButton, 2);
			extentTest.info("Forgot Password Is Clickable : " + forgotPasswordButton.isDisplayed());
			forgotPasswordButton.click();
			//ewait.visibilityOf(driver, popup, 2);
		} catch (Exception e) {
			extentTest.info("Forgot Password Button Is Not Clickable..!!!");
		}
	}

	@SuppressWarnings("finally")
	public String popup() {
		//ewait.visibilityOf(driver, popup, 2);
		String popuptext = popup.getText();
		try {
			if (!(popuptext.equals("OTP sent Successfully"))) {
				extentTest.info("Verification Message : " + popuptext);
				//Thread.sleep(1000);
				closePopup.click();
			} else {
				extentTest.info("Verification Message : " + popuptext);
				//Thread.sleep(1000);
				closePopup.click();
			}
		} catch (Exception e) {
			extentTest.info("Popup Is Not Visible...!!!");
		} finally {
			return popuptext;
		}
	}

	public String getOtp() throws Exception {
		String apiUrl = GetGsonTestData.getConfigData().getUserAPIUrl();


//		String requestBody = "{\"email\":\"7020125850@fairplay.club\"}";
      String requestBody = "{\"email\":\"9970041190@superwin.co\"}";

//		String requestBody = "{\"email\":\"7020125850@superwin.co\"}";

		String accessToken = GetGsonTestData.getToken().getAccessToken();

		String desiredKey = GetGsonTestData.getConfigData().getData();

		String desiredKey1 = GetGsonTestData.getConfigData().getMobileVerification();

		String otp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);
		System.out.println(otp);
		return otp;
	}

	public String setOtp(String otp) throws InterruptedException {
		//this.otp = otp;
		int length = otp.length();
		Thread.sleep(1000);
		if (length < 6) {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Error Msg : " + "Not Able To Enter OTP..!!!");
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		} else if (length > 6) {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Error Msg : " + "Not Able To Enter OTP..!!!");
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		} else {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				return null;
			} catch (Exception e) {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		}

//		enterOtp.sendKeys(otp);
//		extentTest.info("OTP : " + otp);
//		try {
//			extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
//		} catch (Exception e) {
//			int i = 0;
//			do {
//				continue;
//			} while (i == 0);
//		}
	}

	public void clearOtp() {
		try {
			obj.doubleClick_delete(driver, enterOtp);
		} catch (Exception e) {
			extentTest.info("Not Able To Clear The OTP Field..!!!");
		}
	}

	public String getStrongPassword() throws IOException {
		String pwd = rand.randomizeStrongPassword();
		return pwd;
	}

	public String getWeakPassword() throws IOException {
		String	pwd = rand.randomizeWeakPassword();
		return pwd;
	}

	public String setPassword(String pwd) {
		//this.pwd = pwd;
		newPassword.sendKeys(pwd);
		extentTest.info("New Password : " + pwd);
		try {
			String errorMsg = errorNewPassword.getText();
			extentTest.info("Password error message : " + errorMsg);
			return errorMsg;
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
			return null;
		}
	}

	public String setConfirmPassword(String pwd) {
		//this.pwd = pwd;
		confirmPassword.sendKeys(pwd);
		extentTest.info("Confirm Password : " + pwd);
		try {
			String errorMsg = errorConfirmPassword.getText();
			extentTest.info("Confirm Password error message : " + errorMsg);
			return errorMsg;
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
			return null;
		}
	}

	@SuppressWarnings("finally")
	public Boolean changePassword() {
		boolean btn = changePassword.isEnabled();
		extentTest.info("Change Password Is Enabled : " + btn);
		try {
			//ewait.elementToBeClickable(driver, changePassword, 2);
			changePassword.click();
			extentTest.info("Change Password Button Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Change Password Button Is Not Clickable..!!!");
		} finally {
			return btn;
		}
	}

	@SuppressWarnings("finally")
	public String succPopup() {
		//ewait.visibilityOf(driver, popup, 2);
		String popupMsg = popup.getText();
		try {
			if (!(popupMsg.equals("Password Changed Successfully"))) {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
			} else {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
			}
		} catch (Exception e) {
			extentTest.info("Popup Is Not Visible...!!!");
		} finally {
			return popupMsg;
		}
	}

	// @SuppressWarnings("deprecation")
	public void resendCode() {

		try {
			resendOtp.click();
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			try {
				extentTest.info("Error Message : " + errorMsg.getText());
			} catch (Exception e) {
				int i = 0;
				do {
					continue;
				} while (i == 0);
			}
		} catch (Exception e) {
			extentTest.info("Resend OTP Is Not Clickable..!!!");
		}
	}

}
