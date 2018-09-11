package com.example.selenium.pages.mythaistarCarlos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiConfirmBookPage extends BasePage {
  public static final By sendSearch = By.className("mat-dialog-container");

  @Override
  public boolean isLoaded() {

    WebElement sendButton = getDriver().findElementDynamic(sendSearch);
    return sendButton.isDisplayed();

  }

  @Override
  public void load() {

    BFLogger.logError("MyThaiStar booking confirmation page was not loaded.");
  }

  @Override
  public String pageTitle() {

    return "";
  }

  public void confirmBookingData() {

    WebElement sendButton = getDriver().findElementDynamics(By.tagName("button")).get(10);
    System.out.println(sendButton.getAttribute("class"));
    sendButton.click();
  }

}
