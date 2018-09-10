package com.example.selenium.tests.tests.mythaistar;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.example.selenium.pages.mythaistar.ThaiBookPage;
import com.example.selenium.pages.mythaistar.ThaiConfirmBookPage;
import com.example.selenium.pages.mythaistar.ThaiDateTimePage;
import com.example.selenium.pages.mythaistar.ThaiHomePage;
import com.example.selenium.pages.mythaistar.ThaiLoginPage;
import com.example.selenium.pages.mythaistar.ThaiMenuPage;
import com.example.selenium.pages.mythaistar.ThaiReservationsPage;
import com.example.selenium.pages.mythaistar.ThaiSummaryPage;
import com.example.selenium.pages.mythaistar.ThaiWaiterPage;
import com.example.selenium.support.UserMapper;
import com.example.selenium.support.UserMapper.User;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

/**
 * @author jambulud
 */
@RunWith(JUnitParamsRunner.class)
public class MyThaiStarTest extends BaseTest {

  private HashMap<String, String> loginUsers;

  private ThaiHomePage myThaiStarHome;

  private String bookingId;

  private String[] bookingData;

  @Override
  public void setUp() {

    this.myThaiStarHome = new ThaiHomePage();
    this.loginUsers = new HashMap<>();
    this.bookingId = "CB_20170510_123502655Z";
    this.bookingData = new String[] { "Jackie Chan", "kungfu@fakemail.com", "8" };
  }

  @Override
  public void tearDown() {
    // TASK Auto-generated method stub

  }

  @Test
  @FileParameters(value = "src/test/resources/datadriven/test_users.csv", mapper = UserMapper.class)
  public void login_logout_bookMenu(User user) {

    ThaiLoginPage loginPage = this.myThaiStarHome.clickLogInButton();

    loginPage.enterCredentials(user.getUsername(), user.getPassword());
    Assert.assertTrue("Usuario no logeado", this.myThaiStarHome.isUserLogged(user.getUsername()));

    if (user.getUsername().equals("waiter")) {
      ThaiWaiterPage thaiWaiterPage = new ThaiWaiterPage();
      ThaiReservationsPage thaiReservationsPage = thaiWaiterPage.switchToReservations();
      BFLogger.logInfo(thaiReservationsPage.getEmailsReservations().toString());

    } else {
      orderMenu();
    }

    if (!user.getUsername().equals("fakeuser")) {
      logOut();
    }
  }

  // @Test
  public void bookTableJA() {

    ThaiBookPage bookPage = this.myThaiStarHome.clickBookTable();
    bookPage.acceptTermsJA();
  }

  public void orderMenu() {

    ThaiMenuPage menuPage = this.myThaiStarHome.clickMenuButton();
    ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
    summaryPage.orderMenu(this.bookingId);
  }

  public void logOut() {

    this.myThaiStarHome.clickLogOutButton();
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
