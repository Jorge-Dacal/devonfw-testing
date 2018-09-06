package com.example.selenium.pages.mythaistarCarlos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiLoginPage extends BasePage {
	
	private static final By	usernameSearch		= By.name("username");
	private static final By	passwordSearch		= By.name("password");
	private static final By	acessButtonSearch	= By.xpath("button[@type=\"submit\"]");
	
	@Override
	public boolean isLoaded() {
		// getDriver().waitForPageLoaded();
		WebElement usernameTextBox = getDriver().findElementDynamic(passwordSearch);
		// System.out.println("Antes del isDisplayed");
		return usernameTextBox.isDisplayed();
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar login page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public void enterCredentials(String username, String password) {
		System.out.println("Ha pasado al enter credentials");
		WebElement usernameTextBox = getDriver().findElementDynamic(usernameSearch);
		WebElement passwordTextBox = getDriver().findElementDynamic(passwordSearch);
		WebElement accessButton;
		
		usernameTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);
		
		getDriver().waitUntilElementIsClickable(acessButtonSearch);
		accessButton = getDriver().findElementDynamic(acessButtonSearch);
	}
}
