/**
 * 
 */
package com.example.selenium.pages.mythaistar;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * @author jambulud
 */
public class ThaiWaiterPage extends BasePage {
	
	private static final By	ordersTabSearch			= By.xpath("//a[@routerlink='/orders']");
	private static final By	reservationsTabSearch	= By.xpath("//a[@routerlink='/reservations']");
	
	@Override
	public boolean isLoaded() {
		// TASK Auto-generated method stub
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("orders");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar login page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		// TASK Auto-generated method stub
		return "";
	}
	
	public ThaiOrdersPage switchToOrders() {
		getDriver().findElementDynamic(ordersTabSearch)
				.click();
		
		return new ThaiOrdersPage();
	}
	
	public ThaiReservationsPage switchToReservations() {
		getDriver().findElementDynamic(reservationsTabSearch)
				.click();
		
		return new ThaiReservationsPage();
	}
	
}
