package com.superwin365.com;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.main.BaseClass;

public class BonusWalletDetails extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();

	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[1]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light']")
	WebElement normalWalletToggleBtnOff;

	@FindBy(xpath = "(//div[@class = 'drawer-user-balance']//div[@class = 'v-list-item__title font-weight-bold white--text pl-2 f14'])[3]")
	WebElement bonusWallet;

	@FindBy(xpath = "(//div[@class = 'v-list v-sheet theme--light v-list--dense'])[3]//div[@class = 'list_border v-list-item theme--light']")
	WebElement bonus;

	@FindBy(xpath = "(//div[@class = 'v-list v-sheet theme--light v-list--dense'])[3]//div[@class = 'v-list-item theme--light']")
	WebElement bonusNetExposure;

//	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[3]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light white--text']")
//	WebElement bonusWalletToggleBtnON;

	@FindBy(xpath = "(//div[@class = 'py-0 px-2 col primary'])[2]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light']")
	WebElement bonusWalletToggleBtnOff;

	@FindBy(xpath = "(//div[@class = 'v-list-item__content']//div//button[@type = 'button'])[2]")
	WebElement bonusInfoBtn;

	@FindBy(xpath = "(//div[@class = 'rounded-lg v-card v-sheet theme--light']//div[@class = 'br-10 v-card v-card--flat v-sheet theme--light'])")
	WebElement bonusInfoPopup;

	@FindBy(xpath = "//div[@class = 'primary fw-900 pa-0']")
	WebElement bonusInfoTitle;

	@FindBy(xpath = "//div[@class = 'py-1 d-flex justify-space-between border-bottom col col-12']")
	List<WebElement> bonusInfo;

	@FindBy(xpath = "(//button[@class = 'v-expansion-panel-header'])")
	WebElement bonusWalletDropdown;

	@FindBy(xpath = "//div[@class = 'v-expansion-panel-content__wrap']")
	WebElement bonusWalletPanel;

	@FindBy(xpath = "(//div[@class = 'py-1 d-flex justify-space-between col col-6']//span)[1]")
	WebElement bonusStatusOnPanel;

	@FindBy(xpath = "//div[@class = 'text-right col col-6']//button")
	WebElement cancelBonusBtnOnPanel;

	@FindBy(xpath = "//div[@class = 'row text-center center no-gutters align-center justify-center']//div[@class = 'py-1 d-flex justify-space-between border-bottom col col-12']")
	List<WebElement> bonusDetails;

	@FindBy(xpath = "//div[@class = 'py-1 d-flex justify-space-between col col-12']//span")
	List<WebElement> bonusExpiryDetails;

	@FindBy(xpath = "//div[@class = 'd-flex justify-end']//a//span[@class = 'v-btn__content']")
	WebElement viewMore;
	
	@FindBy(xpath = "//div[@class = 'v-toolbar__title tooltitle white--text font-weight-bold pl-0 body-2 text-capitalize']")
	WebElement pendingBonusTransferTitle;
	
	@FindBy(xpath = "(//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div//div//div[@class = 'v-card__subtitle py-2 px-0 caption'])[1]")
	WebElement pendingBonusAmt;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div//div//div[@class = 'v-card__subtitle py-2 px-0 caption'])[2]")
	WebElement pendingBonusStatus;

	@FindBy(xpath = "//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div[@class = 'row no-gutters']//div[@class = 'text-right col col-4']//button")
	WebElement pendingBonusCancelBtn;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[1]")
	WebElement pendingBonusTitle;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'mb-3 col col-6'])[1]")
	WebElement pendingBonusRequestDate;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'mb-3 col col-6'])[2]")
	WebElement pendingBonusExpireDate;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[2]//button")
	WebElement pendingBonusComments;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[2]//div[@class = 'v-card__subtitle caption pa-1 black--text']")
	WebElement pendingBonusCommentsText;

	@FindBy(xpath = "//div//a[@class = 'text-center text-uppercase blue--text py-3 fw-900 f14']")
	WebElement termsAndConditionsBtn;

	@FindBy(xpath = "//div[@class = 'v-card v-sheet theme--light']")
	WebElement termsAndConditionsPopup;

	@FindBy(xpath = "//div[@class = 'v-card v-sheet theme--light']//div[@class = 'v-card__title elevation-2']")
	WebElement termsAndConditionsTitle;

	@FindBy(xpath = "//div[@class = 'v-card__text']//div[@class = 'v-list-item__content']//h2")
	WebElement containtTitle;

	@FindBy(xpath = "//div[@class = 'v-card__actions']//button")
	WebElement closeTermsAndConditionsBtn;

	
	@SuppressWarnings("static-access")
	public BonusWalletDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String turnONNormalWallet() {
		try {
			act.jSForClick(driver, normalWalletToggleBtnOff);
			extentTest.info("Normal Wallet Turned ON..!!!");
			return "Normal Wallet Turned ON..!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Turned ON Normal Wallet...!!!");
			return "Not Able To Turned ON Normal Wallet...!!!";
		}
	}

	public String turnONBonusWallet() {
		try {
			act.jSForClick(driver, bonusWalletToggleBtnOff);
			extentTest.info("Normal Wallet Turned Off & Bonus Wallet Turned ON");
			return "Bonus Wallet Turned ON...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Turned Off Normal Wallet...!!!");
			return "Bonus Wallet Turned ON...!!!";
		}
	}
	
	public String getBonusText() {
		try {
			extentTest.info(bonusWallet.getText());
			return "Able To Fetch The Bonus Text...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Text...!!!");
			return "Not Able To Fetch The Bonus Text...!!!";
		}
	}

	public String getBonusWalletDetails() {
		try {
			extentTest.info(bonus.getText());
			return "Able To Fetch The Bonus Amount...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Amount...!!!");
			return "Not Able To Fetch The Bonus Amount...!!!";
		}
	}

	public String getBonusNetExposureAmount() {
		try {
			extentTest.info(bonusNetExposure.getText());
			return "Able To Fetch The Net Exposure Amount...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Net Exposure Amount...!!!");
			return "Not Able To Fetch The Net Exposure Amount...!!!";
		}
	}

	public String clickOnBonusInfoBtn() {
		try {
			bonusInfoBtn.click();
			extentTest.info("Able To Click On Bonus Info Button...!!!");
			return "Able To Click On Bonus Info Button...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Click On Bonus Info Button...!!!");
			return "Not Able To Click On Bonus Info Button...!!!";
		}
	}

	public Boolean visibilityOfBonusInfoPopup() {
		try {
			ewait.visibilityOf(driver, bonusInfoPopup, 4);
			Boolean data = bonusInfoPopup.isDisplayed();
			extentTest.info("Bonus Information Popup Is Displayed...!!!");
			return data;
		} catch (Exception e) {
			extentTest.info("Sports Bonus Information Popup Is Not Displayed...!!!");
			return false;
		}
	}
	
	public String fetchBonusInfoTitle() {
		try {
			String title = bonusInfoTitle.getText();
			extentTest.info(title);
			return title;
		}catch (Exception e) {
			extentTest.info("Bonus Information Popup Is Not Displayed...!!!");
			return "";
		}
	}	
	
	public String fetchBonusInfo() {
		try {
			for(WebElement info : bonusInfo) {
				extentTest.info(info.getText());
			}
			return "Able To Fetch The Bonus Info...!!!";
		}catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Info...!!!");
			return "Not Able To Fetch The Bonus Info...!!!";
		}
	}
	
	public String clickOnBonusWalletDropdown() {
		try {
			act.jSForClick(driver, bonusWalletDropdown);
			Thread.sleep(1000);
			Boolean data = bonusWalletPanel.isDisplayed();
			extentTest.info("Bonus Wallet Panel Is Visible : " + data);
			return "Bonus Wallet Panel Is Visible...!!!";
		}catch (Exception e) {
			extentTest.info("Bonus Wallet Panel Is Not Visible...!!!");
			return "Bonus Wallet Panel Is Not Visible...!!!";
		}
	}
	
	public void getBonusDetails() {
		try {
			extentTest.info(bonusStatusOnPanel.getText());
			
			extentTest.info(cancelBonusBtnOnPanel.getText());
			
			for(WebElement data : bonusExpiryDetails) {
				extentTest.info(data.getText());
			}
			
			act.jSForClick(driver, bonusWalletDropdown);
			
		}catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Details...!!!");
		}
	}
	
	public void clickOnViewMore() {
		try {
			extentTest.info(viewMore.getText());
			viewMore.click();
			ewait.visibilityOf(driver, pendingBonusTransferTitle, 6);
			extentTest.info("Page Redirected To : " + driver.getCurrentUrl());
			extentTest.info("Redirected Page Title : " + pendingBonusTransferTitle.getText());
		} catch (Exception e) {
			extentTest.info("Page Didnt Got Redirected...!!!");
		}
	}
	
	public void getSportsBonusTransferDetails() {
		try {
			extentTest.info("Pending Bonus Transfer Details..!!!");

			extentTest.info(pendingBonusAmt.getText());
			extentTest.info(pendingBonusStatus.getText());
			extentTest.info(pendingBonusTitle.getText());
			extentTest.info(pendingBonusRequestDate.getText());
			extentTest.info(pendingBonusExpireDate.getText());
			extentTest.info(pendingBonusComments.getText());

			pendingBonusComments.click();

			extentTest.info(pendingBonusCommentsText.getText());

			pendingBonusComments.click();

		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Pending Bonus Transfer Details...!!!");
		}
	}

	public void clickOnTermsAndConditions() {
		try {
			extentTest.info(termsAndConditionsBtn.getText());
			termsAndConditionsBtn.click();

			ewait.visibilityOf(driver, termsAndConditionsPopup, 2);
			Boolean data = termsAndConditionsPopup.isDisplayed();
			extentTest.info("TermsAndConditionsPopup Is Visible : " + data);

			extentTest.info(termsAndConditionsTitle.getText());

			act.jsForScrollDown(driver);

			extentTest.info(closeTermsAndConditionsBtn.getText());

			ewait.elementToBeClickable(driver, closeTermsAndConditionsBtn, 2);
			closeTermsAndConditionsBtn.click();

			extentTest.info("Able To Click On Close Button...!!!");
		} catch (Exception e) {

			extentTest.info("Not Able To Get Terms And Conditions Details...!!!");

		}

	}


}
