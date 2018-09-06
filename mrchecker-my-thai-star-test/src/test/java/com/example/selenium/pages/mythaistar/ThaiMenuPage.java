/**
 * 
 */
package com.example.selenium.pages.mythaistar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * @author jambulud
 */
public class ThaiMenuPage extends BasePage {
	
	private static final By menuButtonSearch = By.xpath("//button[@class=\"text-upper mat-button mat-accent\"]");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("menu");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar menu page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		// TASK Auto-generated method stub
		return "";
	}
	
	public ThaiSummaryPage clickFirstMenu() {
		WebElement menu = getDriver().findElementDynamic(menuButtonSearch);
		menu.click();
		
		return new ThaiSummaryPage();
	}
	
}
