package com.example.selenium.tests.tests.mythaistarCarlos;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.pages.mythaistarCarlos.ThaiBookPage;
import com.example.selenium.pages.mythaistarCarlos.ThaiConfirmBookPage;
import com.example.selenium.pages.mythaistarCarlos.ThaiHomePage;
import com.example.selenium.pages.mythaistarCarlos.User;

public class BookTableTest extends BaseTest {

  private String[] bookingData;

  private ThaiHomePage myThaiStarHome;

  private String name = "John Johnson";

  private String email = "john@fakemail.com";

  private String amountOfGuests = "8";

  private User user = new User(this.name, this.email, this.amountOfGuests);

  @Override
  public void setUp() {

    this.myThaiStarHome = new ThaiHomePage();
    this.bookingData = new String[] { this.user.getUsername(), this.user.getEmail(), this.user.getAmountOfGuests() };
  }

  @Override
  public void tearDown() {
    // TASK Auto-generated method stub

  }

  @Test
  public void shouldResultReturn() {

    ThaiBookPage myBookPage = this.myThaiStarHome.clickBookTable();

    ThaiConfirmBookPage myComfirmPage = myBookPage.enterBookingData(this.bookingData[0], this.bookingData[1],
        this.bookingData[2]);
    myComfirmPage.confirmBookingData();

    myBookPage.checkConfirmationDialog();

  }

}
