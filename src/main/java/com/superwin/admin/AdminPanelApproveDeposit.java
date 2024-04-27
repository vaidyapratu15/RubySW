package com.superwin.admin;

import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.superwin.library.ActionClass;
import com.superwin.library.ExplicitWaits;
import com.superwin.library.FluentWaitsClass;
import com.superwin.library.RandomizeData;
import com.superwin.library.gson_Model.GetGsonTestData;

public class AdminPanelApproveDeposit extends Admin_BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "(//button[@class = 'btn dropdown-toggle'])[2]")
	WebElement transactionsBtn;

	@FindBy(xpath = "(//ul[@class = 'dropdown-menu show'])//a")
	List<WebElement> transaction_dropdownMenu;

	// @FindBy(xpath = "(//ul[@class = 'dropdown-menu show'])//a[@href =
	// '/transactions']")
	// WebElement transaction;

	@FindBy(xpath = "(//div[@class = 'form-group input-group-alternative input-group']//input[@class = 'form-control'])[2]")
	WebElement searchByEmail;

	@FindBy(xpath = "(//button[@class = 'btn btn-block btn-primary btn-md'])[1]")
	WebElement search;

	@FindBy(xpath = "((//tbody[@class = 'list']//tr)[1]//td)[3]")
	WebElement userMail;

	@FindBy(xpath = "((//tbody[@class = 'list']//tr)[1]//td)[5]//div//span")
	WebElement userDepositStatus;

	@FindBy(xpath = "(//li[@class = 'dropdown nav-item dropdown'])[2]//a//i")
	WebElement changeStatus;

	@FindBy(xpath = "//ul[@class = 'dropdown-menu dropdown-menu-right pr-2 show']//a")
	List<WebElement> statusDropdown;

	@SuppressWarnings("static-access")
	public AdminPanelApproveDeposit(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnTransactionsBtn() {
		try {
			transactionsBtn.click();
			extentTest.info("Transaction Button Is Clickable..!!");
		} catch (Exception e) {
			extentTest.info("Transaction Button Is Not Clickable..!!");
		}
	}

	public void checkTransactions() {
		for (WebElement trans : transaction_dropdownMenu) {
			if ((trans.getText()).equals("Transactions")) {
				trans.click();
			}
		}
	}

	public void searchByEmail() {
		try {
			searchByEmail.sendKeys(GetGsonTestData.getNewUserData().getQuery());
		} catch (Exception e) {

		}
	}

	public void searchDepositTransactions() {
		try {
			search.click();
			extentTest.info("Search Button Is Clickable...!!!");
		} catch (Exception e) {
			extentTest.info("Search Button Is Not Clickable...!!!");
		}
	}

	public void approveDepositRequest() throws FileNotFoundException {
		
		if (userMail.getText().equals(GetGsonTestData.getNewUserData().getQuery())   &&  userDepositStatus.getText().equals("Pending") ) {
			ewait.visibilityOf(driver, changeStatus, 2);
			changeStatus.click();
					for(WebElement approveDeposit : statusDropdown) {  
						if((approveDeposit.getText()).equals("Completed")) {
							approveDeposit.click();
							extentTest.info("Deposit Approved..!!!");
						}
					}
		}
	}

}
