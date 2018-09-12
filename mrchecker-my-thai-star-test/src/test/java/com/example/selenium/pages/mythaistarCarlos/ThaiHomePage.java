/**
 *
 */
package com.example.selenium.pages.mythaistarCarlos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;

/**
 * @author jambulud
 */
public class ThaiHomePage extends BasePage {

  private static final By loginButtonSearch = By.xpath("//button[@class=\"mat-icon-button ng-star-inserted\"]");

  private static final String bookTableButtonXpath = "//*[@id=\"homeCard\"]/own-home-card[1]/mat-card/mat-card-actions/button";

  private static final By bookTableButtonSearch = By.xpath(bookTableButtonXpath);

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
    getDriver().manage().window().maximize();
  }

  @Override
  public String pageTitle() {

    return "My Thai Star";
  }

  public ThaiLoginPage clickLogInButton() {

    WebElement loginButton = getDriver().findElementDynamic(loginButtonSearch);
    loginButton.click();

    return new ThaiLoginPage();
  }

  public ThaiBookPage clickBookTable() {

    WebElement bookTableButton = getDriver().findElementDynamic(bookTableButtonSearch);
    bookTableButton.click();
    return new ThaiBookPage();

  }

}
