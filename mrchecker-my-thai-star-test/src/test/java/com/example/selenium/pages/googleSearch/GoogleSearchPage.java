package com.example.selenium.pages.googleSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;

public class GoogleSearchPage extends BasePage {
	
	private static final By selectorGoogleSearchInput = By.cssSelector("#lst-ib");
	
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
		getDriver().get("https://www.google.com/");
	}
	
	@Override
	public String pageTitle() {
		return "Google";
	}
	
	public GoogleResultPage enterGoogleSearchInput(String searchText) {
		WebElement googleSearchInput = getDriver().findElementDynamic(selectorGoogleSearchInput);
		googleSearchInput.sendKeys(searchText);
		googleSearchInput.submit();
		
		return new GoogleResultPage();
	}
}