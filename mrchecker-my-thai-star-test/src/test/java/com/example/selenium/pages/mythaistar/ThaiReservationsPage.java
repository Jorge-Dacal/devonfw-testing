/**
 *
 */
package com.example.selenium.pages.mythaistar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.example.selenium.support.Reservation;

/**
 * @author jambulud
 */
public class ThaiReservationsPage extends BasePage {

  /* Search criteria */
  private static final By reservationsTableSearch = By.xpath("//tbody[@class='td-data-table-body']/tr");

  private static final By reservationRowSearch = By.xpath("./td//span");

  private static final By nextPageSearch = By.xpath("//button[@class=\"td-paging-bar-next-page mat-icon-button\"]");

  private static final By pruebasSearch = nextPageSearch;// By.xpath("//button[3]/span/mat-icon");

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
   * Method to get the reservations for a given email
   *
   * @return List<String> a list of booking ids for that email
   */
  public List<String> findReservationsIdByEmail(String email) {

    return this.tableData.getOrDefault(email, new LinkedList<String>());
  }

  /**
   * @return
   */
  public Map<String, List<Reservation>> getReservations(Map<String, List<Reservation>> idReservations) {

    List<WebElement> reservations;
    List<WebElement> reservationsRow;
    // Map<String, List<Reservation>> idReservations = new HashMap<>();
    List<Reservation> reservationsByDate;
    String date, id, email;

    reservations = getDriver().findElementDynamics(reservationsTableSearch);

    for (WebElement reservationWe : reservations) {

      // get date, email, id
      // getDriver().waitForPageLoaded();
      reservationsRow = reservationWe.findElements(reservationRowSearch);

      // get email
      date = reservationsRow.get(0).getText();
      email = reservationsRow.get(1).getText();
      id = reservationsRow.get(2).getText();

      System.out.printf("date: %s, email: %s, id: %s\n", date, email, id);

      reservationsByDate = idReservations.getOrDefault(date, new LinkedList<Reservation>());
      reservationsByDate.add(new Reservation(date, email, id));

      idReservations.put(date, reservationsByDate);

    }

    return idReservations;
  }

  /**
   * @return
   */
  public Map<String, List<Reservation>> getAllReservations() {

    Map<String, List<Reservation>> idReservations = new HashMap<>();
    WebElement nextPage = getDriver().findElementDynamic(nextPageSearch);
    int i = 0;

    boolean b = false;
    while (!b) {
      System.out.println("PRUEBA " + i + " " + b);
      idReservations = getReservations(idReservations);

      JavascriptExecutor js = ((JavascriptExecutor) getDriver());
      i++;

      nextPage.click();
      // (getDriver().waitForPageLoaded();
      // getDriver().waitForElement(nextPageSearch);
      nextPage = getDriver().findElementDynamic(nextPageSearch, 35);
      b = (Boolean) js.executeScript("return arguments[0].disabled", nextPage);
    }

    return idReservations;
  }

  /**
   * @param date
   * @return
   */
  public boolean AreThereReservations(String date) {

    return getAllReservations().getOrDefault(date, null) == null;
  }

  public ThaiTableBodyPage nextPage() {

    Button nextPage = getDriver().elementButton(pruebasSearch);
    nextPage.click();

    return new ThaiTableBodyPage();
  }

  public Map<String, List<Reservation>> getAllReservationsRare() {

    ThaiTableBodyPage thaiTable = new ThaiTableBodyPage();
    Map<String, List<Reservation>> idReservations = new HashMap<>();
    idReservations = thaiTable.getReservations(idReservations);

    int i = 1;

    while (thaiTable.isThereANextPage()) {
      System.out.println("PRUEBA: " + i);
      thaiTable = thaiTable.nextPage();
      idReservations = thaiTable.getReservations(idReservations);
      i++;
    }
    ;

    return idReservations;
  }

}
