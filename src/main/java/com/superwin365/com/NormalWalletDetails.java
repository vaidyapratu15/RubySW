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

public class NormalWalletDetails extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();

	@FindBy(xpath = "//span//a[@class = 'v-btn v-btn--outlined v-btn--rounded v-btn--router theme--dark v-size--default superBlue--text mr-1']//span//span[@class = 'user-balance white--text']")
	WebElement walletAmt;

	@FindBy(xpath = "//button[@type = 'button']//span//span[contains(text(),'Profile')]")
	WebElement profileBtn;

	@FindBy(xpath = "//div[@class = 'drawer-user-balance']")
	WebElement tripleWalletDetails;

	@FindBy(xpath = "(//div[@class = 'drawer-user-balance']//div[@class = 'v-list-item__title font-weight-bold white--text pl-2 f14'])[1]")
	WebElement normalWallet;

	@FindBy(xpath = "((//div[@class = 'v-list v-sheet theme--light v-list--dense'])[1]//div[@class = 'list_border v-list-item theme--light'])")
	List<WebElement> walletAmt_netExposure;

	@FindBy(xpath = "((//div[@class = 'v-list v-sheet theme--light v-list--dense'])[1]//div[@class = 'pb-2 v-list-item theme--light'])")
	WebElement availableWithdrawal;

	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[1]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light white--text']")
	WebElement normalWalletToggleBtnON;

	@FindBy(xpath = "(//div[@class = 'pa-0 v-list-item theme--light'])[1]//div[@class = 'v-list-item__action']//div[@class = 'v-input--switch__thumb theme--light']")
	WebElement normalWalletToggleBtnOff;

	@FindBy(xpath = "//div[contains(text(),'Betting P&L')]")
	WebElement bettingPnL;

	@FindBy(xpath = "//div[@class = 'col col-8']//div//span[contains(text(),'Deposit')]")
	WebElement depositPnL;

	@FindBy(xpath = "//div[@class = 'v-expansion-panel-content pa-0 border-bt-lr']//div[contains(text(),'Transaction')]")
	WebElement transactionPnL;

	@FindBy(xpath = "(//div[@class = 'v-expansion-panel-content pa-0 border-bt-lr']//div[@class = 'col col-8']//div)[4]")
	WebElement settledDatePnL;

	@FindBy(xpath = "(//div[@class = 'v-expansion-panel-content pa-0 border-bt-lr']//div[@class = 'pl-0 col col-4']//div[@class = 'd-flex'])[1]")
	WebElement commPnL;

	@FindBy(xpath = "(//div[@class = 'v-expansion-panel-content pa-0 border-bt-lr']//div[@class = 'pl-0 col col-4']//div[@class = 'd-flex'])[2]")
	WebElement netWinPnL;

	@SuppressWarnings("static-access")
	public NormalWalletDetails(WebDriver driver) {
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

	public void getWalletAmt() {
		try {
			extentTest.info("Wallet Amount Is : " + walletAmt.getText());
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Wallet Amount...!!!");
		}
	}

	public String clickOnProfileBtn() {
		try {
			profileBtn.click();
			extentTest.info("Profile Button Is Clickable...!!!");
			return "Profile Button Is Clickable...!!!";
		} catch (Exception e) {
			extentTest.info("Profile Button Is Not Clickable...!!!");
			return "Profile Button Is Not Clickable...!!!";
		}
	}

	public String getNormalWalletText() {
		try {
			extentTest.info(normalWallet.getText());
			return "Able To Fetch Normal Wallet Text...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch Normal Wallet Text...!!!");
			return "Not Able To Fetch Normal Wallet Text...!!!";
		}
	}

	public String getNormalWalletDetails() {
		try {
			for (WebElement data : walletAmt_netExposure) {
				extentTest.info(data.getText());
			}
			return "Able To Fetch Wallet Amount and Net Exposure Amount...!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch Wallet Amount and Net Exposure Amount...!!!");
			return "Not Able To Fetch Wallet Amount and Net Exposure Amount...!!!";
		}
	}

	public String getNormalWalletAvailableWithdrawal() {
		try {
			extentTest.info(availableWithdrawal.getText());
			return "Able To Fetch Available Withdrawal Balance...!!!";

		} catch (Exception e) {
			extentTest.info("Not Able To Fetch Available Withdrawal Balance...!!!");
			return "Not Able To Fetch Available Withdrawal Balance...!!!";
		}
	}

	public void clickOnBettingPnL() {
		try {
			profileBtn.click();
			bettingPnL.click();
			extentTest.info("Able To Click On Betting P&L");
		} catch (Exception e) {
			extentTest.info("Not Able To Click On Betting P&L");
		}
	}

	public void fetcgBettingPnLDetails() {
		
			//extentTest.info( depositPnL.getText() );
			extentTest.info( transactionPnL.getText() );
			extentTest.info( settledDatePnL.getText() );
			extentTest.info( commPnL.getText() );
			extentTest.info( netWinPnL.getText() );
		
	}
}

//div[@class = 'drawer-user-balance']//div[@class = 'v-list-item__title font-weight-bold white--text pl-2 f14']