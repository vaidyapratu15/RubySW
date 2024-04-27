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

public class SportsWalletDetails extends BaseClass{
	
	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();
	
	@FindBy(xpath = "(//div[@class = 'drawer-user-balance']//div[@class = 'v-list-item__title font-weight-bold white--text pl-2 f14'])[2]")
	WebElement sportsWallet;

	@FindBy(xpath = "(//div[@class = 'v-list v-sheet theme--light v-list--dense'])[2]//div[@class = 'list_border v-list-item theme--light']")
	WebElement sportBonus;

	@FindBy(xpath = "(//div[@class = 'v-list v-sheet theme--light v-list--dense'])[2]//div[@class = 'v-list-item theme--light']")
	WebElement netExposure;

	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[2]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light white--text']")
	WebElement sportsWalletToggleBtnON;

	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[2]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light']")
	WebElement sportsWalletToggleBtnOff;

	@FindBy(xpath = "(//div[@class = 'v-list-item__content']//div//button[@type = 'button'])[1]")
	WebElement sportsBonusInfoBtn;

	@FindBy(xpath = "(//div[@class = 'rounded-lg v-card v-sheet theme--light']//div[@class = 'br-10 v-card v-card--flat v-sheet theme--light'])")
	WebElement sportsBonusInfoPopup;

	@FindBy(xpath = "//div[@class = 'primary fw-900 pa-0']")
	WebElement sportsBonusInfoTitle;

	@FindBy(xpath = "//div[@class = 'py-1 d-flex justify-space-between border-bottom col col-12']")
	List<WebElement> sportsBonusInfo;

	@FindBy(xpath = "(//button[@class = 'v-expansion-panel-header'])")
	WebElement sportsBonusWalletDropdown;

	@FindBy(xpath = "//div[@class = 'v-expansion-panel-content__wrap']")
	WebElement sportsBonusWalletPanel;

	@FindBy(xpath = "(//div[@class = 'py-1 d-flex justify-space-between col col-6']//span)[1]")
	WebElement sportsBonusStatusOnPanel;

	@FindBy(xpath = "//div[@class = 'text-right col col-6']//button")
	WebElement cancelSportsBonusBtnOnPanel;

	@FindBy(xpath = "//div[@class = 'row text-center center no-gutters align-center justify-center']//div[@class = 'py-1 d-flex justify-space-between border-bottom col col-12']")
	List<WebElement> sportsBonusDetails;

	@FindBy(xpath = "//div[@class = 'py-1 d-flex justify-space-between col col-12']//span")
	List<WebElement> sportsBonusExpiryDetails;

	@FindBy(xpath = "//div[@class = 'd-flex justify-end']//a//span[@class = 'v-btn__content']")
	WebElement viewMore;
	
	@FindBy(xpath = "//div[@class = 'v-toolbar__title tooltitle white--text font-weight-bold pl-0 body-2 text-capitalize']")
	WebElement pendingSportsBonusTransferTitle;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div//div//div[@class = 'v-card__subtitle py-2 px-0 caption'])[1]")
	WebElement pendingSportsBonusAmt;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div//div//div[@class = 'v-card__subtitle py-2 px-0 caption'])[2]")
	WebElement pendingSportsBonusStatus;

	@FindBy(xpath = "//div[@class = 'v-card__text py-1 pr-4 grey darken-4']//div[@class = 'row no-gutters']//div[@class = 'text-right col col-4']//button")
	WebElement pendingSportsBonusCancelBtn;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[1]")
	WebElement pendingSportsBonusTitle;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'mb-3 col col-6'])[1]")
	WebElement pendingSportsBonusRequestDate;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'mb-3 col col-6'])[2]")
	WebElement pendingSportsBonusExpireDate;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[2]//button")
	WebElement pendingSportsBonusComments;

	@FindBy(xpath = "(//div[@class = 'v-card__text py-1']//div[@class = 'row no-gutters']//div[@class = 'col col-12'])[2]//div[@class = 'v-card__subtitle caption pa-1 black--text']")
	WebElement pendingSportsBonusCommentsText;

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
	public SportsWalletDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String turnONSportsWallet() {
		try {
			act.jSForClick(driver, sportsWalletToggleBtnOff);
			extentTest.info("Sports Bonus Wallet Turned ON...!!!");
			return "Sports Bonus Wallet Turned ON...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Turned Off Sports Wallet...!!!");
			return "Not Able To Turned Off Sports Wallet...!!!";
		}
	}
	
	public String getSportsWalletText() {
		try {
			extentTest.info(sportsWallet.getText());
			return "Able To Fetch The Sports Bonus Text...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Sports Bonus Text...!!!");
			return "Not Able To Fetch The Sports Bonus Text...!!!";
		}
	}

	public String getSportsWalletDetails() {
		try {
			extentTest.info(sportBonus.getText());
			return "Able To Fetch The Sports Bonus Amount...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Sports Bonus Amount...!!!");
			return "Not Able To Fetch The Sports Bonus Amount...!!!";
		}
	}

	public String getSportsNetExposureAmount() {
		try {
			extentTest.info(netExposure.getText());
			return "Able To Fetch The Net Exposure Amount...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Net Exposure Amount...!!!");
			return "Not Able To Fetch The Net Exposure Amount...!!!";
		}
	}

	public String clickOnSportsBonusInfoBtn() {
		try {
			sportsBonusInfoBtn.click();
			extentTest.info("Able To Click On Sports Bonus Info Button...!!!");
			return "Able To Click On Sports Bonus Info Button...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Click On Sports Bonus Info Button...!!!");
			return "Not Able To Click On Sports Bonus Info Button...!!!";
		}
	}

	public Boolean visibilityOfSportsBonusInfoBonus() {
		try {
			ewait.visibilityOf(driver, sportsBonusInfoPopup, 4);
			Boolean data = sportsBonusInfoPopup.isDisplayed();
			extentTest.info("Sports Bonus Information Popup Is Displayed...!!! ");
			return data;
		} catch (Exception e) {
			extentTest.info("Sports Bonus Information Popup Is Not Displayed...!!!");
			return false;
		}
	}

	public String fetchSportsBonusInfoTitle() {
		try {
			String title = sportsBonusInfoTitle.getText();
			extentTest.info(title);
			return title;
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The sportsBonusInfo Title...!!!");
			return "";
		}
	}

	public String fetchSportsBonusInfo() {
		try {
			for (WebElement info : sportsBonusInfo) {
				extentTest.info(info.getText());
			}
			return "Able To Fetch The Bonus Info...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Info...!!!");
			return "Not Able To Fetch The Bonus Info...!!!";
		}
	}

	public String clickOnSportsBonusWalletDropdown() throws InterruptedException {
		try {
			act.jSForClick(driver, sportsBonusWalletDropdown);
			// sportsBonusWalletDropdown.click();
			Thread.sleep(1000);
			Boolean data = sportsBonusWalletPanel.isDisplayed();
			extentTest.info("Sports Bonus Wallet Panel Is Visible : " + data);
			return "Sports Bonus Wallet Panel Is Visible...!!!";
		} catch (Exception e) {
			extentTest.info("Sports Bonus Wallet Panel Is Not Visible...!!!");
			return "Sports Bonus Wallet Panel Is Not Visible...!!!";
		}
	}

	public void getSportsBonusDetails() {
		try {
			extentTest.info(sportsBonusStatusOnPanel.getText());

			extentTest.info(cancelSportsBonusBtnOnPanel.getText());

			for (WebElement data : sportsBonusDetails) {
				extentTest.info(data.getText());
			}

			for (WebElement data : sportsBonusExpiryDetails) {
				extentTest.info(data.getText());
			}

			act.jSForClick(driver, sportsBonusWalletDropdown);
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bonus Details...!!!");
		}

	}

	public void clickOnViewMore() {
		try {
			extentTest.info(viewMore.getText());
			viewMore.click();
			ewait.visibilityOf(driver, pendingSportsBonusTransferTitle, 6);
			extentTest.info("Page Redirected To : " + driver.getCurrentUrl());
			extentTest.info("Redirected Page Title : " + pendingSportsBonusTransferTitle.getText());
		} catch (Exception e) {
			extentTest.info("Page Didnt Got Redirected...!!!");
		}
	}
	
	public void getSportsBonusTransferDetails() {
		try {
			extentTest.info("Pending Sports Bonus Transfer Details..!!!");

			extentTest.info(pendingSportsBonusAmt.getText());
			extentTest.info(pendingSportsBonusStatus.getText());
			extentTest.info(pendingSportsBonusTitle.getText());
			extentTest.info(pendingSportsBonusRequestDate.getText());
			extentTest.info(pendingSportsBonusExpireDate.getText());
			extentTest.info(pendingSportsBonusComments.getText());

			pendingSportsBonusComments.click();

			extentTest.info(pendingSportsBonusCommentsText.getText());

			pendingSportsBonusComments.click();

		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Pending Sports Bonus Transfer Details...!!!");
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
