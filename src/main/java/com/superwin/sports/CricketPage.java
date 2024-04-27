package com.superwin.sports;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;
import com.superwin.main.BaseClass;
import com.superwin.main.Turnover;

public class CricketPage extends BaseClass {

	ActionClass action = new ActionClass();
	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();
	RandomizeData rand = new RandomizeData();

	@FindBy(xpath = "//button[@type = 'button']//span//i[@class = 'v-icon notranslate mdi mdi-reload theme--dark white--text']")
	WebElement refreshBtn;
	
	@FindBy(xpath = "(//a[@role='tab']//following::a[1])[1]")
	private WebElement cricketBtn;

	@FindBy(xpath = "//div[@class = 'py-6 v-card v-sheet theme--light px-10']")
	private WebElement videoPopup;

	@FindBy(xpath = "//div[@class = 'py-6 v-card v-sheet theme--light px-10']//button")
	private WebElement cancelVideoPopup;

	@FindBy(xpath = "(//div[contains(@class,'text-right')])[1]") // div[contains(@class,'caption px-3')]
	private WebElement backBtn;

	@FindBy(xpath = "//div[contains(text(),'Currently')]")
	private WebElement noMatches;

	@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
	private WebElement betPlaceMsg;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[3]")
	private WebElement firstRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[4]")
	private WebElement firstRowLay;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[9]")
	private WebElement secondRowBack;

	@FindBy(xpath = "((//div[contains(@class,'v-expansion-panel')])[2]//button[contains(@class,'odds')])[10]")
	private WebElement secondRowLay;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input[@type='text'])[4]")
	private WebElement stakevalue;

	@FindBy(xpath = "//button[@type='submit']") // span[text()='Place Bet ']
	private WebElement placeBtn;

	@FindBy(xpath = "//span[text()='Cancel']")
	private WebElement unmatchedCancelBtn;

	@FindBy(xpath = "//span[@class='match-title']")
	private List<WebElement> cricketEvents;

	@FindBy(xpath = "(//span[@class='match-title'])[1]")
	private WebElement firstCricketEvent;

	@FindBy(xpath = "//div[contains(text(),'Stake value should be greater than 1')]")
	private WebElement stakeValuePopup;

	@FindBy(xpath = "//div[contains(text(),'Back')]")
	private WebElement backText;

	@FindBy(xpath = "//div[@class = 'v-application v-application--is-ltr theme--dark']")
	private WebElement randomClick;

	@FindBy(xpath = "(((//div[@class='v-window__container'])[1]//div[contains(@class,'market-block')])[1]//span[text()='Suspended'])[1]")
	private WebElement matchOddStatus;

	@FindBy(xpath = "//span[contains(text(),'Match Odds')]")
	private WebElement matchOddsBtn;

	@FindBy(xpath = "//div[text()='CashOut']")
	private WebElement cashOutBtn;

	@FindBy(xpath = "((//span[@class='v-btn__content'])[15]//span)[4]")
	private WebElement firstCashoutOdds;

	@FindBy(xpath = "((//span[@class='v-btn__content'])[22]//span)[4]")
	private WebElement secondCashoutOdds;

	
	@SuppressWarnings("static-access")
	public CricketPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@SuppressWarnings("deprecation")
	public void refreshPage() {
		refreshBtn.click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void clickRandomly() {
		randomClick.click();
	}

	public void clickPlayexchCricketbtn() {
		cricketBtn.click();
	}

	public void videoPopup() {
		try {
			boolean data = videoPopup.isDisplayed();
			if (data == true) {
				cancelVideoPopup.click();
			}
		} catch (Exception e) {
			extentTest.info("Video Is Not Visible...!!!");
		}
	}

	public void getPlayexchCricketEvents() {
		extentTest.info("Total Events Available : " + cricketEvents.size());
		for (WebElement ele : cricketEvents) {
			extentTest.info(ele.getText());
		}
	}

	public void setPlayexchMatch(WebDriver driver) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.textToBePresentInElement(backBtn, "Back"));
			extentTest.info("Match is present");
		} catch (Exception e) {
			extentTest.info("No Match Available");
		}
	}

	public String getPlayexchCurrentlyMatches() {
		try {
			String text = noMatches.getText();
			return text;
		} catch (Exception e) {
		}
		return null;
	}

	public String getPlayexchCricketBetPlaceMsg() {
		String msg = betPlaceMsg.getText();
		return msg;
	}

	public void FirstMatchName() {
		extentTest.info(firstCricketEvent.getText());
	}

	public void clickPlayexchFHorseBack() {
		try {
			action.explicitWaitClickable(driver, firstRowBack);
			extentTest.info("First Horse Back is clickable");
		} catch (Exception e) {
			extentTest.info("First Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchFHorseLay() {
		try {
			action.explicitWaitClickable(driver, firstRowLay);
			extentTest.info("First Horse Lay is clickable");
		} catch (Exception e) {
			extentTest.info("First Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexchSHorseBack() {
		try {
			action.explicitWaitClickable(driver, secondRowBack);
			extentTest.info("Second Horse Back is clickable");
		} catch (Exception e) {
			extentTest.info("Second Horse Back is NOT clickable");
		}
	}

	public void clickPlayexchSHorseLay() {
		try {
			action.explicitWaitClickable(driver, secondRowLay);
			extentTest.info("Second Horse Lay is clickable");
		} catch (Exception e) {
			extentTest.info("Second Horse Lay is NOT clickable");
		}
	}

	public void clickPlayexchUnmatchedCancelBtn() {
		unmatchedCancelBtn.click();
	}

	public void getPlayexchStakeValue() {
		String entervalue = Turnover.turnover;
		stakevalue.sendKeys(entervalue);
		extentTest.info("Stake Value : " + entervalue);
	}

	public void clickPlayexchPlaceBtn() {
		try {
			extentTest.info("Place Bet Button is Enabled : " + placeBtn.isEnabled());
			placeBtn.click();
			Thread.sleep(5000);
			ewait.visibilityOf(driver, betPlaceMsg, 10);
			extentTest.info(betPlaceMsg.getText());
		} catch (Exception e) {
		}
	}

	
	
}
