package com.example.selenium.pages.mythaistar;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/*
 * public class Time{
 * String hour;
 * String minute;
 * }
 * public class Date{
 * String day;
 * String month;
 * String year;
 * }
 */

public class ThaiBookPage extends BasePage {
	
	
	
	private static final By inputFieldsSearch = By.xpath("//div[@class='mat-form-field-infix']");
	
	
	private static final By	dateSearch		= By.cssSelector("input[formcontrolname='bookingDate']");
	private static final By	nameSearch		= By.cssSelector("input[formcontrolname='name']");
	private static final By	emailSearch		= By.cssSelector("input[formcontrolname='email']");
	private static final By	guestsSearch	= By.cssSelector("input[formcontrolname='assistants']");
	private static final By	checkboxSearch	= By.className("mat-checkbox-inner-container");
	
	private static final String	xpathBookTableButton	= "//*[@id='mat-tab-content-0-0']/div/div/div[2]/form/div[3]/button";
	private static final By		bookTableButtonSearch	= By.xpath(xpathBookTableButton);
	private static final By		headerSearch			= By.tagName("h3");
	
	
	private static final String xpathDateTime = "//*[@id=\"mat-tab-content-1-0\"]/div/div/div[2]/form/div[2]/mat-form-field[1]/div/div[1]/div";
	private static final By dateTimeSearch = By.xpath(xpathDateTime);
	private static final By dateTimeInputSearch = By.tagName("input");
	
	
	
	private static final String	xpathConfirmationDialog	= "//*[@id='cdk-overlay-29']/snack-bar-container/simple-snack-bar";
	private static final By		dialogSearch			= By.xpath(xpathConfirmationDialog);
	
	@Override
	public boolean isLoaded() {
		String text = "You can invite your friends to lunch or book a table";
		WebElement header = getDriver().findElementDynamic(headerSearch);
		String headerText = header.getText();
		//System.out.println("header" + headerText+ headerText.equals(text));
		return headerText.equals(text);
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar book page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public ThaiDateTimePage enterTimeAndDate() {
		// reserva para una hora despues dando click a la flecha de "+1 hora"
		// entonces hace click en el icono de "visto"
		//List<WebElement> inputFields = new ArrayList<WebElement>();
		//inputFields = getDriver().findElementDynamics(inputFieldsSearch);
		WebElement dateInput = getDriver().findElementDynamic(dateSearch);
		//WebElement dateInput = inputFields.get(2);
		dateInput.click();
		return new ThaiDateTimePage();
		
	}
	
	public void enterName(String name) {
		WebElement nameInput = getDriver().findElementDynamic(nameSearch);
		nameInput.sendKeys(name);
	}
	
	public void enterEmail(String email) {
		WebElement emailInput = getDriver().findElementDynamic(emailSearch);
		emailInput.sendKeys(email);
	}
	
	public void enterGuests(String amountOfGuests) {
		WebElement guestsInput = getDriver().findElementDynamic(guestsSearch);
		guestsInput.sendKeys(amountOfGuests);
	}
	
	public void acceptTerms() {
		WebElement checkbox = getDriver().findElementDynamic(checkboxSearch);
		checkbox.click();
	}
	
	public void clickBookTable() {
		WebElement bookTableButton = getDriver().findElementDynamic(bookTableButtonSearch);
		getDriver().waitUntilElementIsClickable(bookTableButtonSearch);
		bookTableButton.click();
	}
	
	public ThaiConfirmBookPage enterBookingData(String name, String email, String amountOfGuests) {
		// enterTimeAndDate(time, date);
		enterName(name);
		enterEmail(email);
		enterGuests(amountOfGuests);
		acceptTerms();
		clickBookTable();
		
		return new ThaiConfirmBookPage();
	}
	
	public void checkConfirmationDialog() {
		WebElement confirmationDialog = getDriver().findElementDynamic(dialogSearch);
		System.out.println(confirmationDialog.getAttribute("class"));
	}
	
}

/*
 * System.out.println("Ha pasado al enter credentials");
 * WebElement usernameTextBox = getDriver().findElementDynamic(usernameSearch);
 * WebElement passwordTextBox = getDriver().findElementDynamic(passwordSearch);
 * WebElement accessButton;
 * usernameTextBox.sendKeys(username);
 * passwordTextBox.sendKeys(password);
 * getDriver().waitUntilElementIsClickable(acessButtonSearch);
 * accessButton = getDriver().findElementDynamic(acessButtonSearch);
 */
