package com.superwin.main;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Turnover extends BaseClass {

	@FindBy(xpath = "//span[@class='font-weight-bold white--text']")
	WebElement awaitingBonus;

	@FindBy(xpath = "((//div[@class='card-rounded mx-auto bonus-card v-card v-sheet theme--dark'])[1]//span)[1]")
	WebElement bonusAmt;

	@FindBy(xpath = "(//div[@class='d-flex justify-space-between caption'])[1]//div[2]")
	WebElement tunoverAmt;

	@FindBy(xpath = "(//span[@aria-haspopup='true'][normalize-space()='cancel bonus'])[1]")
	WebElement cancleBonus;

	@FindBy(xpath = "//button//span[contains(text(),'Yes')]")
	WebElement yes;

	@FindBy(xpath = "(//button//span[contains(text(),'No')])[3]")
	WebElement no;

	@FindBy(xpath = "//div[@class = 'row py-1 no-gutters justify-center']//div[@class = 'col-sm-6 col-md-6 col-lg-4 col-12 pa-1']")
	List<WebElement> bonuslist;

	@FindBy(xpath = "(//div[@aria-label = 'Fairplay Logo']//div[@class = 'v-responsive__content'])[1]")
	WebElement fairplayLogo;

	@FindBy(xpath = "(//a[@class = 'v-btn v-btn--outlined v-btn--rounded v-btn--router theme--dark v-size--default primary--text mr-1']//span[@class = 'v-btn__content']//span)[1]")
	WebElement walletAmount;

	@SuppressWarnings("static-access")
	public Turnover(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static String turnover;

	public void clickOnAwaitingBonus() {
		try {
			awaitingBonus.click();
			extentTest.info("Awaiting Bonus Is Clickable...!!!");
		} catch (Exception e) {
			extentTest.info("Awaiting Bonus Is Not Clickable...!!!");
		}
	}

	@SuppressWarnings("unused")
	public void countBonus() {
		int count = 0;
		for (WebElement bonuscount : bonuslist) {
			count = count + 1;
		}
		System.out.println(count);
	}

	public String getBonusAmt() {
		String bamt = bonusAmt.getText();
		extentTest.info("Bonus Amount Is : " + bamt);
		return bamt;
	}

	public String getTurnoverAmt() {
		String turnover = tunoverAmt.getText();
		extentTest.info("TurnOver Amount Is : " + turnover);
		Turnover.turnover = turnover;
		return Turnover.turnover;
	}

	public String checkBonusAmt() throws InterruptedException {
		String bamt = getBonusAmt();
		int bonusAmtLength = bamt.length();
		if (bonusAmtLength >= 2) {
			String turnover = getTurnoverAmt();
			int turnoverAmtLength = turnover.length();
			if (turnoverAmtLength <= 4) {
				String gameStatus = clickOnFPLogo();
				return gameStatus;
			} else {
				cancleBonus.click();
				Thread.sleep(1000);
				yes.click();
				extentTest.info("Try Next Bonus..!!!");
				return "Bonus Got Cancel..!!!";
			}
		} else {
			cancleBonus.click();
			Thread.sleep(1000);
			yes.click();
			extentTest.info("Try Next Bonus..!!!");
			return "Bonus Got Cancel..!!!";
		}

	}

	public String clickOnFPLogo() {
		try {
			fairplayLogo.click();
			extentTest.info("Fairplay Logo Is Clickable..!!!");
			return "User Is Playing Game..!!!";
		} catch (Exception e) {
			extentTest.info("Fairplay Logo Is Not Clickable..!!!");
			return "User Is Not Able To Play Game..!!!";
		}

	}

	public void depositTurnoverAmt() throws InterruptedException {
		String walletAmt = walletAmount.getText();
		if (!walletAmt.equals(turnover)) {
			loginpage.profileClick();
			deposit.checkWalletAmount();
			deposit.depositNow();
			deposit.depositThroughUPI(turnover, "fdgsdcgsdfrwe");
			deposit.checkTransactions();
			Thread.sleep(1000);
			deposit.getTransactionDetails();
		} else {
			int i = 0;
			while (i == 0) {
				break;
			}
		}
	}

}
