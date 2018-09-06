/**
 * 
 */
package com.example.selenium.pages.mythaistar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * @author jambulud
 */
public class ThaiReservationsPage extends BasePage {
	
	private static final By				reservationsTableSearch	= By.xpath("//table[@class='td-data-table-body']/tr");
	private static final By				reservationRowSearch	= By.tagName("span");
	private Map<String, List<String>>	tableData;
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("reservations");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar reservation page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		// TASK Auto-generated method stub
		return "";
	}
	
	public Map<String, List<String>> getEmailsReservations() {
		tableData = new HashMap<String, List<String>>();
		List<WebElement> reservations = getDriver().findElementDynamics(reservationsTableSearch);
		List<WebElement> reservationsRow;
		List<String> ids;
		String email, id;
		
		for (WebElement reservation : reservations) {
			// get date, email, id
			reservationsRow = reservation.findElements(reservationRowSearch);
			email = reservationsRow.get(1)
					.getText();
			id = reservationsRow.get(2)
					.getText();
			ids = tableData.getOrDefault(email, new LinkedList<String>());
			
			ids.add(id);
			
			tableData.put(email, ids);
		}
		
		return tableData;
		
	}
	
	public List<String> findReservationsIdByEmail(String email) {
		return tableData.getOrDefault(email, new LinkedList<String>());
	}
	
}
