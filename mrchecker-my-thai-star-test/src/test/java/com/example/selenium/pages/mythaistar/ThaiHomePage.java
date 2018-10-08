/**
 *
 */
package com.example.selenium.pages.mythaistar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

/**
 * @author jambulud
 */
// @Configuration
// @PropertySource("file:config.properties")
public class ThaiHomePage extends BasePage {

  /* Search criteria */
  private static final String mythaistarUrl = "http://mts-angular-my-thai-star-mrcheck.10.36.39.36.nip.io/";

  private static final By loginButtonSearch = By.cssSelector("button.mat-icon-button:nth-child(6)");

  private static final By logoutButtonSearch = By.xpath("//button[@class=\"mat-icon-button\"]");

  private static final By logoutItemSearch = By.xpath("//button[@class=\"mat-menu-item\"]");

  private static final By labelLoginSearch = By.xpath("//span[@class='forDesktop']");

  private static final By menuTabSearch = By.xpath("//a[@routerlink='/menu']");

  private static final By bookTableButtonSearch = By
      .xpath("//*[@id=\"homeCard\"]/own-home-card[1]/mat-card/mat-card-actions/button");

  @Override
  public boolean isLoaded() {

    if (getDriver().getTitle().equals(pageTitle())) {
      return true;
    }
    return false;
  }

  @Override
  public void load() {

    getDriver().get(mythaistarUrl);
    getDriver().manage().window().maximize();
  }

  @Override
  public String pageTitle() {

    return "My Thai Star";
  }

  /**
   * Seek for the login button and click it
   *
   * @return ThaiLoginPage an object that represents the login page
   */
  public ThaiLoginPage clickLogInButton() {

    WebElement loginButton = getDriver().findElementDynamic(loginButtonSearch);
    loginButton.click();

    return new ThaiLoginPage();
  }

  /**
   * Seek for the login button and logs out
   *
   */
  public void clickLogOutButton() {

    WebElement logoutButton = getDriver().findElementDynamic(logoutButtonSearch);
    logoutButton.click();

    /*
     * WebElement logoutItem = getDriver().findElementDynamic(logoutItemSearch); logoutItem.click();
     */
    String scriptClick = "var we = document.getElementsByClassName(\"mat-menu-item\"); we[we.length-1].click();";
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript(scriptClick);
  }

  /**
   * Seek for the menu button and click it
   *
   * @return ThaiMenuPage an object that represents the reservations page
   */
  public ThaiMenuPage clickMenuButton() {

    WebElement menuTab = getDriver().findElementDynamic(menuTabSearch);
    menuTab.click();

    return new ThaiMenuPage();
  }

  /**
   * Checks whether an user is logged or not
   *
   * @param username The user to be checked
   * @return boolean true if the user is logged else false
   */
  public boolean isUserLogged(String username) {

    try {
      List<WebElement> accessButton = getDriver().findElementDynamics(labelLoginSearch);
      // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + accessButton.size());
      // System.out
      // .println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB: " + accessButton.get(0).getText().equals(username));
      if (accessButton.size() > 0 && accessButton.get(0).getText().equals(username)) {
        return true;
      }
    } catch (BFElementNotFoundException e) {
    }

    return false;
  }

  public boolean isUserLogged() {

    try {
      List<WebElement> accessButton = getDriver().findElementDynamics(labelLoginSearch, 3);
      if (accessButton.size() > 0 && accessButton.get(0).getText().length() > 0) {
        return true;
      }
    } catch (BFElementNotFoundException e) {
    }

    return false;
  }

  public ThaiBookPage clickBookTable() {

    WebElement bookTableButton = getDriver().findElementDynamic(bookTableButtonSearch);
    bookTableButton.click();
    return new ThaiBookPage();

  }
}
