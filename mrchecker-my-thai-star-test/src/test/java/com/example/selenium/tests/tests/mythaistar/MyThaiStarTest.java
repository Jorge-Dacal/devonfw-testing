package com.example.selenium.tests.tests.mythaistar;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.pages.mythaistar.ThaiBookPage;
import com.example.selenium.pages.mythaistar.ThaiConfirmBookPage;
import com.example.selenium.pages.mythaistar.ThaiDateTimePage;
import com.example.selenium.pages.mythaistar.ThaiHomePage;
import com.example.selenium.pages.mythaistar.ThaiLoginPage;
import com.example.selenium.pages.mythaistar.ThaiMenuPage;
import com.example.selenium.pages.mythaistar.ThaiSummaryPage;

/**
 * @author jambulud
 */
public class MyThaiStarTest extends BaseTest {

  private HashMap<String, String> loginUsers;

  private ThaiHomePage myThaiStarHome;

  private String bookingId;

  private String[] bookingData;

  @Override
  public void setUp() {

    this.myThaiStarHome = new ThaiHomePage();
    this.loginUsers = new HashMap<>();
    this.loginUsers.put("user0", "password");
    this.loginUsers.put("userfake", "passfake");
    this.loginUsers.put("waiter", "waiter");
    this.bookingId = "CB_20170510_123502595Z";
    this.bookingData = new String[] { "Jackie Chan", "kungfu@fakemail.com", "8" };
  }

  @Override
  public void tearDown() {
    // TASK Auto-generated method stub

  }

  // @Test
  public void loginUser() {

    ThaiLoginPage loginPage = this.myThaiStarHome.clickLogInButton();

    loginPage.enterCredentials("userfake", this.loginUsers.get("userfake"));
    Assert.assertFalse("Usuario logeado", this.myThaiStarHome.isUserLogged("userfake"));
    loginPage = this.myThaiStarHome.clickLogInButton();

    loginPage.enterCredentials("user0", this.loginUsers.get("user0"));
    Assert.assertTrue("Usuario no logeado", this.myThaiStarHome.isUserLogged("user0"));
  }

  @Test
  public void bookTableJA() {

    ThaiBookPage bookPage = this.myThaiStarHome.clickBookTable();
    bookPage.acceptTermsJA();
  }

  // @Test
  public void orderMenu() {

    ThaiMenuPage menuPage = this.myThaiStarHome.clickMenuButton();
    ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
    summaryPage.orderMenu(this.bookingId);
  }

  // @Test
  public void bookTable() {

    ThaiBookPage myBookPage = this.myThaiStarHome.clickBookTable();
    ThaiDateTimePage myDateTimePage = myBookPage.enterTimeAndDate();
    myDateTimePage.setUpDateAndTime();

    ThaiConfirmBookPage myComfirmPage = myBookPage.enterBookingData(this.bookingData[0], this.bookingData[1],
        this.bookingData[2]);
    myComfirmPage.confirmBookingData();

    myBookPage.checkConfirmationDialog();

  }
}
