package com.example.selenium.tests.tests.mythaistar;

import java.util.HashMap;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.example.selenium.pages.mythaistar.ThaiBookPage;
import com.example.selenium.pages.mythaistar.ThaiConfirmBookPage;
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
      // BFLogger.logInfo("Reservations objects");
      // BFLogger.logInfo(thaiReservationsPage.getAllReservations().toString()); --> "BUENA"
      BFLogger.logInfo(thaiReservationsPage.getAllReservations().toString());

    } else {
      bookTable(user.getUsername(), getRandomEmail(), "2");
      orderMenu();
    }

    if (!user.getUsername().equals("fakeuser")) {
      logOut();
    }
  }

  public void orderMenu() {

    ThaiMenuPage menuPage = this.myThaiStarHome.clickMenuButton();
    ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
    summaryPage.orderMenu(this.bookingId);
  }

  public void logOut() {

    this.myThaiStarHome.clickLogOutButton();
  }

  public void bookTable(String username, String email, String guests) {

    ThaiBookPage myBookPage = this.myThaiStarHome.clickBookTable();

    ThaiConfirmBookPage myComfirmPage = myBookPage.enterBookingData(username, email, guests);
    String[] data = myComfirmPage.confirmBookingData();
    System.out.printf("DATA: %s, %s, %s\n", data[0], data[1], data[2]);
    myBookPage.checkConfirmationDialog();

  }

  public String getRandomEmail() {

    Random random = new Random();
    int rint = random.nextInt(100000);
    return "user0_" + rint + "_@fakemail.com";
  }
}
