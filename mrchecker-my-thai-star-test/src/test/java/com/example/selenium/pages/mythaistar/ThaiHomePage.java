/**
 *
 */
package com.example.selenium.pages.mythaistar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

/**
 * @author jambulud
 */
public class ThaiHomePage extends BasePage {

  /* Search criteria */
  private static final By loginButtonSearch = By.xpath("//button[@class=\"mat-icon-button ng-star-inserted\"]");

  private static final By labelLoginSearch = By.xpath("//span[@class='forDesktop']");

  private static final By menuTabSearch = By.xpath("//a[@routerlink='/menu']");

  @Override
  public boolean isLoaded() {

    if (getDriver().getTitle().equals(pageTitle())) {
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
      if (accessButton.size() > 0 && accessButton.get(0).getText().equals(username)) {
        return true;
      }
    } catch (BFElementNotFoundException e) {
    }

    return false;
  }

}
