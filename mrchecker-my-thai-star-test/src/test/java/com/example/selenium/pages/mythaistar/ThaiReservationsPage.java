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

  /* Search criteria */
  private static final By reservationsTableSearch = By.xpath("//tbody[@class='td-data-table-body']/tr");

  private static final By reservationRowSearch = By.xpath(".//span");

  /* Map to store email/reservation id data */
  private Map<String, List<String>> tableData;

  @Override
  public boolean isLoaded() {

    getDriver().waitForPageLoaded();

    return getDriver().getCurrentUrl().contains("reservations");
  }

  @Override
  public void load() {

    BFLogger.logError("MyThaiStar reservation page was not loaded.");

  }

  @Override
  public String pageTitle() {

    return "";
  }

  /**
   * Method to get the reservations by email
   *
   * @return Map<String, List<String>> a map where the key is an email and the value is a list of booking ids for that
   *         email
   */
  public Map<String, List<String>> getEmailsReservations() {

    this.tableData = new HashMap<String, List<String>>();
    List<WebElement> reservations = getDriver().findElementDynamics(reservationsTableSearch);
    List<WebElement> reservationsRow;
    List<String> ids;
    String email, id;

    for (WebElement reservation : reservations) {
      // get date, email, id
      reservationsRow = reservation.findElements(reservationRowSearch);
      // get email
      email = reservationsRow.get(1).getText();
      System.out.printf("date: %s, email %s, id: %s\n", reservationsRow.get(0).getText(),
          reservationsRow.get(1).getText(), reservationsRow.get(2).getText());

      // get reservation id
      id = reservationsRow.get(2).getText();
      ids = this.tableData.getOrDefault(email, new LinkedList<String>());

      ids.add(id);

      this.tableData.put(email, ids);
    }

    return this.tableData;

  }

  /**
   * Method to get the reservations for a given email
   *
   * @return List<String> a list of booking ids for that email
   */
  public List<String> findReservationsIdByEmail(String email) {

    return this.tableData.getOrDefault(email, new LinkedList<String>());
  }

}
