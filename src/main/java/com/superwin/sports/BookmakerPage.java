package com.superwin.sports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;
import com.superwin.main.BaseClass;

public class BookmakerPage extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();
	RandomizeData rand = new RandomizeData();
	
	@FindBy(xpath = "(//span[@class='match-title'])[1]")
	private WebElement firstCricketEvent;
	
	@FindBy(xpath = "//div[contains(text(),'FANCY')]")
	private WebElement fancyBtn;
	
	@FindBy(xpath = "//span[@class = 'align-self-center white--text font-weight-bold text-uppercase premium-title mr-2']//span[contains(text(),'Fancy')]")
	private WebElement fancyMatch;

	@FindBy(xpath = "//div[@class = 'd-flex align-center v-card v-card--flat v-sheet theme--light rounded-0']//button[@class = 'btn-odds black--text font-weight-bold odds-width multiline-btn text-center v-btn v-btn--has-bg v-btn--tile theme--light v-size--small accent']")
	private WebElement fBack;
	
	@FindBy(xpath = "//div[@class = 'd-flex align-center v-card v-card--flat v-sheet theme--light rounded-0']//button[@class = 'btn-odds black--text font-weight-bold odds-width multiline-btn text-center v-btn v-btn--has-bg v-btn--tile theme--light v-size--small secondary']")
	private WebElement fLay;

	
	@SuppressWarnings("static-access")
	public BookmakerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnFirstMatch() {
		firstCricketEvent.click();
	}
	
	public void searchFancyMatch() {
		ewait.visibilityOf(driver, fancyBtn, 4);
		act.jSForScroll(driver, fancyBtn);
		fancyBtn.click();
		extentTest.info("Fancy Match Is Available..!!!");
	}
	
	public void placeFBackBet() {
		fBack.click();
	}

	public void placeFLayBet() {
		fLay.click();
	}

}
