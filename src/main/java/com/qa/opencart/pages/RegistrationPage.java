package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscriberYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value ='1']");
	private By subscriberNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value ='0']");
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type ='submit' and @value='Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {

		eleutil.waitForElementToBeVisible(this.firstName, Constants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleutil.doActionsSendKeys(this.lastName, lastName);
		eleutil.doActionsSendKeys(this.email, email);
		eleutil.doActionsSendKeys(this.telephone, telephone);
		eleutil.doActionsSendKeys(this.password, password);
		eleutil.doActionsSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscriberYes);
		} else
			eleutil.doClick(subscriberNo);

		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueBtn);

		if (getAccountRegisterSuccessNsg().contains(Constants.REGISTER_SUCCESS_MSG)) {
			goToRegisterPage();
			return true;
		}
		return false;
	}

	public String getAccountRegisterSuccessNsg() {
		return eleutil.waitForElementToBeVisible(successMsg, Constants.DEFAULT_TIME_OUT).getText();
	}

	private void goToRegisterPage() {
		eleutil.doClick(logoutLink);
		eleutil.doClick(registerLink);
	}

}
