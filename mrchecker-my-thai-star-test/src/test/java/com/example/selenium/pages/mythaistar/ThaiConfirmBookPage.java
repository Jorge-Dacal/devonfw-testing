package com.example.selenium.pages.mythaistar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiConfirmBookPage extends BasePage {
  public static final By sendSearch = By.className("mat-dialog-container");

  public static final By dataTextSearch = By.xpath("//div[@class=\"justify-space-between\"]/span");

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

  public String[] confirmBookingData() {

    List<WebElement> dataText = getDriver().findElementDynamics(dataTextSearch);
    String date = dataText.get(1).getText();
    String name = dataText.get(2).getText();
    String email = dataText.get(3).getText();

    WebElement sendButton = getDriver().findElementDynamics(By.tagName("button")).get(10);
    System.out.println(sendButton.getAttribute("class"));
    sendButton.click();

    return new String[] { date, name, email };
  }

}
