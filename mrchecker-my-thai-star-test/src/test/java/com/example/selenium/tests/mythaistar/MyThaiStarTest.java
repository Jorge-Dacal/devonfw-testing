package com.example.selenium.tests.mythaistar;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.common.data.Reservation;
import com.example.selenium.common.data.User;
import com.example.selenium.common.data.UserMapper;
import com.example.selenium.common.utils.Utils;
import com.example.selenium.pages.mythaistar.ThaiBookPage;
import com.example.selenium.pages.mythaistar.ThaiConfirmBookPage;
import com.example.selenium.pages.mythaistar.ThaiHomePage;
import com.example.selenium.pages.mythaistar.ThaiLoginPage;
import com.example.selenium.pages.mythaistar.ThaiMenuPage;
import com.example.selenium.pages.mythaistar.ThaiReservationsPage;
import com.example.selenium.pages.mythaistar.ThaiSummaryPage;
import com.example.selenium.pages.mythaistar.ThaiWaiterPage;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

/**
 * @author jambulud
 */
@RunWith(JUnitParamsRunner.class)
public class MyThaiStarTest extends BaseTest {

  private ThaiHomePage myThaiStarHome = new ThaiHomePage();

  @Override
  public void setUp() {

    this.myThaiStarHome.load();
    logOut();
  }

  @Override
  public void tearDown() {

    // TASK Auto-generated method stub

  }

  // @Test
  // @FileParameters(value = "src/test/resources/datadriven/test_users.csv", mapper = UserMapper.class)
  public void Test_loginAndLogOut(User user) {

    login(user);
    logOut();
  }

  // @Test
  public void Test_loginFake() {

    User userfake = new User("userFake", "passwordfake");
    ThaiLoginPage loginPage = this.myThaiStarHome.clickLogInButton();
    loginPage.enterCredentials(userfake.getUsername(), userfake.getPassword());
    Assert.assertFalse("User " + userfake.getUsername() + " logged",
        this.myThaiStarHome.isUserLogged(userfake.getUsername()));
  }

  @Test
  @FileParameters(value = "src/test/resources/datadriven/test_users.csv", mapper = UserMapper.class)
  public void Test_bookTable(User user) {

    // Generate data for reservation
    String fakeEmail = Utils.getRandomEmail(user.getUsername());
    String date = Utils.getTomorrowDate();
    int guest = Utils.getRandom1toMax(8);
    Reservation reservation = new Reservation(date, user.getUsername(), fakeEmail, guest);
    User waiter = new User("waiter", "waiter");

    // login(user);
    bookTable(reservation);
    // logOut();
    login(waiter);
    verifyBooking(reservation);
    logOut();
  }

  // @Test
  public void Test_orderMenu() {

    String bookingId = "CB_20170510_123502655Z";
    ThaiMenuPage menuPage = this.myThaiStarHome.clickMenuButton();
    ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
    summaryPage.orderMenu(bookingId);
  }

  private void login(User user) {

    ThaiLoginPage loginPage = this.myThaiStarHome.clickLogInButton();
    loginPage.enterCredentials(user.getUsername(), user.getPassword());
    Assert.assertTrue("User " + user.getUsername() + " not logged",
        this.myThaiStarHome.isUserLogged(user.getUsername()));
  }

  private void logOut() {

    if (this.myThaiStarHome.isUserLogged()) {
      this.myThaiStarHome.clickLogOutButton();
    }
    Assert.assertFalse("Some user logged", this.myThaiStarHome.isUserLogged());
  }

  private void bookTable(Reservation reservation) {

    ThaiBookPage myBookPage = this.myThaiStarHome.clickBookTable();

    ThaiConfirmBookPage myComfirmPage = myBookPage.enterBookingData(reservation);
    myComfirmPage.confirmBookingData();
    myBookPage.checkConfirmationDialog();
  }

  private void verifyBooking(Reservation reservation) {

    ThaiWaiterPage myWaiterPage = new ThaiWaiterPage();
    ThaiReservationsPage myReservationsPage = myWaiterPage.switchToReservations();
    HashMap<String, List<Reservation>> reservations = myReservationsPage.searchDatesByEmail(reservation.getEmail());
    reservation.getDate();
    Assert.assertTrue("Booking not found", reservations.containsKey(reservation.getDate()));
    List<Reservation> reservationsForDate = reservations.get(reservation.getDate());
    Assert.assertFalse("Booking not found", reservationsForDate.isEmpty());
  }

}
