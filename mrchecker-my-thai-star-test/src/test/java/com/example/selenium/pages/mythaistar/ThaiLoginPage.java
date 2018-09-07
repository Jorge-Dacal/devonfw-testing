package com.example.selenium.pages.mythaistar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * @author jambulud
 */
public class ThaiLoginPage extends BasePage {

  /* Search criteria */
  private static final By usernameSearch = By.name("username");

  private static final By passwordSearch = By.name("password");

  private static final By acessButtonSearch = By.xpath("//button[@type='submit']");

  @Override
  public boolean isLoaded() {

    WebElement usernameTextBox = getDriver().findElementDynamic(passwordSearch);
    return usernameTextBox.isDisplayed();
  }

  @Override
  public void load() {

    BFLogger.logError("MyThaiStar login page was not loaded.");
  }

  @Override
  public String pageTitle() {

    return "";
  }

  /**
   * Seek for username and password text fields and writes an username and a password then the accept button is clicked
   *
   * @param username an username to log in
   * @param password the password for the username
   */
  public void enterCredentials(String username, String password) {

    System.out.println("Ha pasado al enter credentials");
    WebDriverWait driverWait = new WebDriverWait(getDriver(), 10);
    WebElement usernameTextBox = getDriver().findElementDynamic(usernameSearch);
    WebElement passwordTextBox = getDriver().findElementDynamic(passwordSearch);
    WebElement accessButton;

    for (char c : username.toCharArray()) {
      usernameTextBox.sendKeys(c + "");
    }

    for (char c : password.toCharArray()) {
      passwordTextBox.sendKeys(c + "");
    }

    driverWait
        .until((driver) -> driver.findElement(passwordSearch).getAttribute("value").length() == password.length());
    accessButton = getDriver().findElementDynamic(acessButtonSearch);
    accessButton.click();
  }
}
