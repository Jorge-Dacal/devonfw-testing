package com.example.selenium.pages.googleSearch;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class GoogleResultPage extends BasePage {
	private static final By selectorResultList = By.cssSelector("#res");
	
	public ListElements getResultList() {
		return getDriver().elementList(selectorResultList);
	}
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		if (getDriver().getCurrentUrl()
				.contains("search")) {
			return true;
		}
		return false;
	}
	
	@Override
	public void load() {
		BFLogger.logError("Google result page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
}
