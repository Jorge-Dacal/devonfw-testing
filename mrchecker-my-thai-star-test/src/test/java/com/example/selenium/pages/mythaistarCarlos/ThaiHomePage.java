/**
 * 
 */
package com.example.selenium.pages.mythaistarCarlos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;

/**
 * @author jambulud
 */
public class ThaiHomePage extends BasePage {
	
	private static final By	loginButtonSearch		= By.xpath("//button[@class=\"mat-icon-button ng-star-inserted\"]");
	private static final By	cardSearch				= By.className("mat-card");
	private static final By	bookTableButtonSearch	= By.tagName("button");
	
	@Override
	public boolean isLoaded() {
		if (getDriver().getTitle()
				.equals(pageTitle())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void load() {
		getDriver().get("http://mts-angular-my-thai-star-mrcheck.10.36.39.36.nip.io/");
	}
	
	@Override
	public String pageTitle() {
		return "My Thai Star";
	}
	
	public ThaiLoginPage clickLogInButton() {
		WebElement loginButton = getDriver().findElementDynamic(loginButtonSearch);
		loginButton.click();
		
		return new ThaiLoginPage();
	}
	
	public ThaiBookPage clickBookTable() {
		WebElement bookTableButton = getDriver().findElementDynamic(cardSearch)
				.findElement(bookTableButtonSearch);
		bookTableButton.click();
		
		return new ThaiBookPage();
	}
	
}
