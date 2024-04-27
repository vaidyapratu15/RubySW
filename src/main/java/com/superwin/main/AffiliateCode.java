package com.superwin.main;

import java.io.FileNotFoundException;
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

public class AffiliateCode extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

//	@FindBy(xpath = "//body/div/div/div[@data-app='true']/div/div/header[@data-booted='true']/div/div/span/form[@novalidate='novalidate']/a[1]")
//	WebElement joinNowBtn;

	@FindBy(xpath = "//span[@class = 'login form before-loggin']//span[contains(text(),'Register')]")
	WebElement registerBtn;

//	@FindBy(xpath = "//div[@class = 'v-card v-sheet theme--dark paddWhats']")
//	WebElement whatsApp;

	@FindBy(xpath = "//div[@justify]//div[contains(text(),'Now create an account easily on whatsapp!')]")
	WebElement whatsApp;

	@FindBy(xpath = "//div[@class = 'pos-abos whatsappicon']")
	WebElement whatsAppIcon;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[1]")
	WebElement enterName;

	@FindBy(xpath = " //div[contains(text(),'name should be atleast 2 characters')]")
	WebElement errMsgName;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[2]")
	WebElement enterNumber;

	@FindBy(xpath = "//div[contains(text(),'Mobile Number is not valid')]")
	WebElement errorMsgNumber;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[3]")
	WebElement enterPassword;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[4]")
	WebElement errMsgPwd;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[4]")
	WebElement confirmPassword;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[5]")
	WebElement errMsgConfirmPwd;

//	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[6]")
//	WebElement enterAffiliateCode;

	@FindBy(xpath = "//input[@placeholder='Affiliate Code']")
	WebElement enterAffiliateCode;

	@FindBy(xpath = "//input[@placeholder='Referral Code']")
	WebElement enterReferralCode;

	@FindBy(xpath = "//button//span[contains(text(),'Register')]")
	WebElement register;

	// https://kheljaa.com/registration2/
	// https://kheljaa.com/registration
	// https://kheljaa.com/quick-signup-2/

	@FindBy(xpath = "//input[@type='number']")
	WebElement enterOtp;

	@FindBy(xpath = "//div[@class='v-messages__message message-transition-enter-to']")
	WebElement errorMsgForOtp;

	// for https://kheljaa.com/fp-registration/
	@FindBy(xpath = "(//div//input[@id='input-39'])[1]")
	WebElement enterOtp1;

	@FindBy(xpath = "//button[@id='confirmBtn']")
	WebElement confirmBtn;

	@FindBy(xpath = "//div[contains(text(),'Signed-Up Successfully.')]")
	WebElement successfulMsg;

	@FindBy(xpath = "//div[contains(text(),'Incorrect OTP, Please Provide Valid OTP!!!')]")
	WebElement otpPopup;

	@FindBy(xpath = "//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "//div[@class = 'v-messages__message pl-left']")
	WebElement errMsg;

	@FindBy(xpath = "(//button//span[contains(text(), 'Accept and Continue')])[2]")
	WebElement acceptCookie;

	@FindBy(xpath = "//div[@class = 'v-input--selection-controls__ripple']")
	WebElement clickCheckBox;

	@FindBy(xpath = "(//button//span[contains(text(), 'Accept and Continue')])[1]")
	WebElement acceptAndContinue;

	@FindBy(xpath = "//div[@class = 'title']")
	WebElement title;

	@FindBy(xpath = "//span[contains(text(),'Profile')]")
	WebElement profile;

	@FindBy(xpath = "(((//div[contains(text(),'Bonus')])[1])//following::div)[1]")
	WebElement bonus;

	@FindBy(xpath = "//div[@class='v-list v-sheet theme--light v-list--dense']//div[@class = 'v-list-item theme--light']")
	List<WebElement> walletDetails;

	@FindBy(xpath = "//span[contains(text(), 'Logout')]")
	WebElement logout;

	@FindBy(xpath = "(//div[@class='v-select__selections'])[1]")
	WebElement selCountryCode;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div")
	List<WebElement> listCountryCode;

	@SuppressWarnings("static-access")
	public AffiliateCode(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String affiliatecode;

	String[] affiliatesCodeArray = { "dgcas", "dg_quicksb", "dg_quickCas", "dg_sb", "new_casdg", "ZEB", "test_dg", "dg",
			"dg_fpta", "dg_fptb", "dg_fptc", "dg_fptp", "dg_fptd", "dg_fpte", "dg_fptf", "dg_fptg", "dg_fpth",
			"dg_fptj", "dg_fptz", "dg_fptx", "dg_fptv", "dg_fptn", "dg_fptm", "dg_fptl", "dg_fptk", "dg_fptq",
			"dg_fptw", "dg_fptr", "dg_fptt", "dg_fptu", "dg_fpt", "dg_fpti" };

	public void clickOnRegisterBtn() {
		try {
			ewait.visibilityOf(driver, registerBtn, 6);
			obj.jSForClick(driver, registerBtn);
			extentTest.info("Register Button Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Register Button Is Not Clickable..!!!");
		}
	}

	public void scrollTillRegister() {
		obj.jSForScroll(driver, register);
	}

	public Boolean checkWhatsApp() {
		try {
			Boolean data = whatsApp.isDisplayed();
			extentTest.info("WhatsApp Is Displayed On Registration Page : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("WhatsApp Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

	public Boolean checkWhatsAppIcon() {
		try {
			Boolean data = whatsAppIcon.isDisplayed();
			extentTest.info("WhatsAppIcon Is Displayed On Registration Page : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("WhatsAppIcon Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

	public void setName() {
		String name = rand.randomizeName();
		enterName.sendKeys(name);
		extentTest.info("Username : " + name);
	}

	public void contryCode(String code) {
		selCountryCode.click();
		for (WebElement countrycode : listCountryCode) {
			String codetext = countrycode.getText();
			if (codetext.equals(code)) {
				extentTest.info("Country code is : " + countrycode.getText());
				countrycode.click();
				ewait.elementToBeClickable(driver, countrycode, 4);
			}
		}
	}

	public String setNumber() {
		String number = rand.randomizeNumber();
		enterNumber.sendKeys(number);
		extentTest.info("Number : " + number);
		return number;
	}

	public String getQuery(String number, String mailhandle) {
		// this.number = number;
		String query = number + mailhandle;
		String reqBody = "{\"email\" : " + "\"" + query + "\"}";
		System.out.println(reqBody);
		return reqBody;
	}

	public String getQueryAE(String number, String mailhandle) {
		// this.number = number;
		String query = number + mailhandle;
		String reqBody = "{\"email\" : " + "\"" + query + "\"}";
		System.out.println(reqBody);
		return reqBody;
	}

	public String setPassword() throws IOException {
		String pwd = rand.randomizeStrongPassword();
		enterPassword.sendKeys(pwd);
		extentTest.info("Password : " + pwd);
		return pwd;
	}

	@SuppressWarnings("finally")
	public String getWeakPassword() throws IOException {
		String pwd = rand.randomizeWeakPassword();
		enterPassword.sendKeys(pwd);
		extentTest.info("Password : " + pwd);
		try {
			extentTest.info("Password error message : " + errMsgPwd.getText());
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
		} finally {
			return pwd;
		}
	}

	public void setConfirmPassword(String pwd) {
		confirmPassword.sendKeys(pwd);
		extentTest.info("Confirm Password : " + pwd);
		try {
			extentTest.info("Confirm Password error message : " + errMsgConfirmPwd.getText());
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
		}
	}

//	public void verifyAffiliateCodeField(String code) {
//		Boolean data = enterAffiliateCode.isDisplayed();
//		if (data.equals(true)) {
//			setAffiliateCode(code);
//		} else {
//			extentTest.info("Affiliate Field Is Not Displayed..!!!");
//		}
//	}
//
//	public void setAffiliateCode(String code) {
//		enterAffiliateCode.sendKeys(code);
//		extentTest.info("Affiliate Code : " + code);
//		try {
//			ewait.elementToBeClickable(driver, enterReferralCode, 2);
//			enterReferralCode.click();
//			extentTest.info("Referral Field Is Clickable..!!!");
//		} catch (Exception e) {
//			extentTest.info("Referral Field Is Not Clickable..!!!");
//		}
//	}
//	
	public String getAffiliateCodeFromDB(String reqBody) throws Exception {
		String apiUrl = GetGsonTestData.getConfigData().getUserAPIUrl();

		String requestBody = reqBody;

		String accessToken = GetGsonTestData.getToken().getAccessToken();

		String desiredKey = GetGsonTestData.getConfigData().getData();

		String desiredKey1 = GetGsonTestData.getConfigData().getAffiliateCode();

		String affiliateCode = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);

		extentTest.info("Affiliate Code Stored In DataBase Is : " + affiliateCode);

		return affiliateCode;
	}

	public void verifyAffiliateCode(String reqBody) throws Exception {
		String affiliatecode = null;
		String url = driver.getCurrentUrl();
		extentTest.info("URL : " + url);
		if (url.contains("btag")) {
			int index = url.indexOf("=") + 1;
			affiliatecode = url.substring(index);
			if (!affiliatecode.equals(null)) {
				for (int i = 0; i < affiliatesCodeArray.length; i++) {
					if (affiliatecode.equals(affiliatesCodeArray[i])) {
						extentTest.info(affiliatecode + " " + "Is Client Provided Affiliate Code..!!!");
						affiliatecode = getAffiliateCodeFromDB(reqBody);
						this.affiliatecode = affiliatecode;
						int j = 0;
						do {
							break;
						} while (j == 0);
					}
				}
			} else if ((!affiliatecode.equals(null))) {
				extentTest.info(affiliatecode + " " + "Is User Provided Affiliate Code..!!!");
				this.affiliatecode = affiliatecode;
			}
		} else {
			affiliatecode = getAffiliateCodeFromDB(reqBody);
			if (!affiliatecode.equals(null)) {
				extentTest.info("URL Contains By Default Affiliate Code : " + affiliatecode);
				this.affiliatecode = affiliatecode;
			} else {
				extentTest.info("URL Does Not Contains Any Affiliate Code...!!!");
				this.affiliatecode = affiliatecode;
			}
		}

	}

//	public String getReferralCode() throws FileNotFoundException {
//		String referralCode = GetGsonTestData.getReferalData().getReferalCode();
//		return referralCode;
//	}
//
//	public void setReferralCode(String referralCode) {
//		enterReferralCode.sendKeys(referralCode);
//		extentTest.info("Referral Code  : " + referralCode);
//		try {
//			ewait.elementToBeClickable(driver, enterAffiliateCode, 2);
//			enterAffiliateCode.click();
//			extentTest.info("Affiliate Field Is Clickable..!!!");
//		} catch (Exception e) {
//			extentTest.info("Affiliate Field Is Not Clickable..!!!");
//		}
//	}

	public Boolean clickRegister() throws IOException {
		Boolean btn = register.isEnabled();
		if (btn.equals(true)) {
			obj.jSForClick(driver, register);
			extentTest.info("Register button is enabled : " + btn);
			return btn;
		} else {
			extentTest.info("Register Button Is Enabled : " + btn);
			return btn;
		}
	}

	public String popup() throws FileNotFoundException {
		ewait.visibilityOf(driver, popup, 2);
		String popupMsg = popup.getText();
		if (!popupMsg.equals(null)) {
			String expectedPopUp = GetGsonTestData.getConfigData().getUserRegistered();
			if (popupMsg.equals(expectedPopUp)) {
				extentTest.info("Verification message : " + popupMsg);
				closePopup.click();
			} else {
				extentTest.info("Verification message : " + popupMsg);
				closePopup.click();
				int i = 0;
				do {
					break;
				} while (i == 0);
			}
		} else {
			extentTest.info("Verification message : " + "Popup Is Not Visible");
		}
		return popupMsg;

	}

	public String getOTPFromDB(String reqBody) throws Exception {

		String apiUrl = GetGsonTestData.getConfigData().getUserAPIUrl();

		String requestBody = reqBody;

		String accessToken = GetGsonTestData.getToken().getAccessToken();

		String desiredKey = GetGsonTestData.getConfigData().getData();

		String desiredKey1 = GetGsonTestData.getConfigData().getMobileVerification();

		String otp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);

		return otp;
	}

	public String setOtp(String otp) {
		try {
			enterOtp.sendKeys(otp);
			extentTest.info("OTP : " + otp);
			try {
				extentTest.info("Error Message For OTP : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Is Valid..!!!");
				return "OTP Is Valid..!!!";
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter OTP..!!!");
			return "Not Able To Enter OTP..!!!";
		}
	}

	public String setOtp1(String otp) {
		try {
			enterOtp1.sendKeys(otp);
			extentTest.info("OTP : " + otp);
			try {
				extentTest.info("Error Message For OTP : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Is Valid..!!!");
				return "OTP Is Valid..!!!";
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter OTP..!!!");
			return "Not Able To Enter OTP..!!!";
		}
	}

	public Boolean clickConfirm() throws IOException {
		Boolean btn = confirmBtn.isEnabled();
		try {
			ewait.elementToBeClickable(driver, confirmBtn, 4);
			confirmBtn.click();
			extentTest.info("ConfirmBtn Button Is Enabled : " + btn);
			return btn;
		} catch (Exception e) {
			extentTest.info("ConfirmBtn Button Is Enabled : " + "False");
			return false;
		}
	}

	public String signupPopup() throws InterruptedException, FileNotFoundException {
		ewait.visibilityOf(driver, popup, 8);
		String popupMsg = popup.getText();
		if (!popupMsg.equals(null)) {
			String expectedPopup = GetGsonTestData.getConfigData().getSignUpSuccessufulMsg();
			if (popupMsg.equals(expectedPopup)) {
				extentTest.info("Verification Message For SignUp :" + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
			} else {
				extentTest.info("Verification Message For SignUp :" + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
				int i = 0;
				do {
					break;
				} while (i == 0);
			}
		} else {
			extentTest.info("PopUp Is Not Visible..!!!");
		}
		return popupMsg;

	}

	public String acceptCookie() {
		ewait.visibilityOf(driver, acceptCookie, 6);
		try {
			ewait.elementToBeClickable(driver, acceptCookie, 6);
			obj.jSForClick(driver, acceptCookie);
			extentTest.info("Accept cookie is clicked...!!!");
			return "Accept cookie is clicked...!!!";
		} catch (Exception e) {
			extentTest.info("Accept cookie is Not clicked...!!!");
			return "Accept cookie is Not clicked...!!!";
		}
	}

	public String clickCheckBox() {
		try {
			ewait.elementToBeClickable(driver, clickCheckBox, 6);
			obj.jSForClick(driver, clickCheckBox);
			extentTest.info("CheckBox is clicked...!!!");
			return "CheckBox is clicked...!!!";
		} catch (Exception e) {
			extentTest.info("CheckBox is Not clicked...!!!");
			return "CheckBox is Not clicked...!!!";
		}
	}

	public Boolean acceptAndContinue() {
		Boolean btn = acceptAndContinue.isEnabled();
		try {
			ewait.elementToBeClickable(driver, acceptAndContinue, 6);
			obj.jSForClick(driver, acceptAndContinue);
			extentTest.info("acceptAndContinue is clicked : " + btn);
			return true;
		} catch (Exception e) {
			extentTest.info("acceptAndContinue is clicked : False");
			return false;
		}
	}

	public Boolean profileClick() {
		try {
			profile.click();
			extentTest.info("Profile Button : " + profile.getText());
			return true;
		} catch (Exception e) {
			extentTest.info("Profile Button Is Not Clickable");
			return false;
		}
	}

	public void getWalletDetails() {
		for (WebElement Amt : walletDetails) {
			extentTest.info(Amt.getText());
		}
	}

	public void bonus() {
		try {
			String bonusvalue = bonus.getText();
			extentTest.info("Bonus : " + bonusvalue);
		} catch (Exception e) {
			extentTest.info("Bonus is Not Availabe");
		}
	}

	public Boolean logout() {
		obj.jSForScroll(driver, logout);
		try {
			logout.click();
			String logout = driver.getTitle();
			extentTest.info(logout);
			return true;
		} catch (Exception e) {
			extentTest.info("User Is Not Able To Do Logout..!!!");
			return false;
		}
	}

}
