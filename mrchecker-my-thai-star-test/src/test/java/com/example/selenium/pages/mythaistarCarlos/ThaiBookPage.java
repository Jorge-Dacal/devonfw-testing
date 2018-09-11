package com.example.selenium.pages.mythaistarCarlos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiBookPage extends BasePage {

  String currentDateTimeString = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());

  private static final By inputFieldsSearch = By.xpath("//div[@class='mat-form-field-infix']");

  private static final By dateSearch = By.cssSelector("input[formcontrolname='bookingDate']");

  private static final By nameSearch = By.cssSelector("input[formcontrolname='name']");

  private static final By emailSearch = By.cssSelector("input[formcontrolname='email']");

  private static final By guestsSearch = By.cssSelector("input[formcontrolname='assistants']");

  private static final By checkboxSearch = By.className("mat-checkbox");

  private static final String xpathBookTableButton = "//*[@id='mat-tab-content-0-0']/div/div/div[2]/form/div[3]/button";

  private static final By bookTableButtonSearch = By.xpath(xpathBookTableButton);

  private static final By headerSearch = By.tagName("h3");

  private static final By dialogSearch = By.className("bgc-green-600");

  @Override
  public boolean isLoaded() {

    String text = "You can invite your friends to lunch or book a table";
    WebElement header = getDriver().findElementDynamic(headerSearch);
    String headerText = header.getText();

    return headerText.equals(text);
  }

  @Override
  public void load() {

    BFLogger.logError("MyThaiStar book page was not loaded.");
  }

  @Override
  public String pageTitle() {

    return "";
  }

  public void enterCurrentTimeAndDate() {

    WebElement dateInput = getDriver().findElementDynamic(dateSearch);
    dateInput.sendKeys(this.currentDateTimeString);

  }

  public void enterName(String name) {

    WebElement nameInput = getDriver().findElementDynamic(nameSearch);
    nameInput.sendKeys(name);
  }

  public void enterEmail(String email) {

    WebElement emailInput = getDriver().findElementDynamic(emailSearch);
    emailInput.sendKeys(email);
  }

  public void enterGuests(String amountOfGuests) {

    WebElement guestsInput = getDriver().findElementDynamic(guestsSearch);
    guestsInput.sendKeys(amountOfGuests);
  }

  public void acceptTerms() {

    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("document.getElementsByClassName('mat-checkbox-inner-container')[1].click();");
    //
    // System.out.println("OBTAIN CHECKBOX PARENT ID");
    // WebElement checkboxParent = getDriver()
    // .findElement(By.xpath("//ancestors::div[@class='mat-checkbox-inner-container']"));
    // System.out.println("OBTAIN CHECKBOX PARENT 2");
    // String checkboxParent_id = checkboxParent.getAttribute("id");
    // System.out.println("CHECKBOX PARENT ID: " + checkboxParent_id);
    // WebElement checkbox = getDriver().findElement(
    // By.cssSelector("#" + checkboxParent_id + " > label.mat-checkbox-layout > div.mat-checkbox-inner-container"));
    // checkbox.click();

  }

  public void clickBookTable() {

    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("document.getElementsByTagName('button')[8].click();");

  }

  public ThaiConfirmBookPage enterBookingData(String name, String email, String amountOfGuests) {

    enterCurrentTimeAndDate();
    enterName(name);
    enterEmail(email);
    enterGuests(amountOfGuests);
    acceptTerms();
    clickBookTable();

    return new ThaiConfirmBookPage();
  }

  public boolean checkConfirmationDialog() {

    WebElement greenConfirmationDialog = getDriver().findElementDynamic(dialogSearch);
    return greenConfirmationDialog.isDisplayed();
  }

}
