/**
 * 
 */
package com.example.selenium.pages.mythaistar;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * @author jambulud
 */
public class ThaiOrdersPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("orders");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar order page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		// TASK Auto-generated method stub
		return "";
	}
	
}
