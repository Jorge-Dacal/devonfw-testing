package com.example.selenium.tests.tests.mythaistar;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.pages.mythaistar.ThaiHomePage;
import com.example.selenium.pages.mythaistar.ThaiLoginPage;
import com.example.selenium.pages.mythaistar.ThaiMenuPage;
import com.example.selenium.pages.mythaistar.ThaiSummaryPage;

/**
 * @author jambulud
 */
public class MyThaiStarTest extends BaseTest {

  private String[][] loginUsers;

  private ThaiHomePage myThaiStarHome;

  private String bookingId = "CB_20170509_123502555Z";

  @Override
  public void setUp() {

    this.myThaiStarHome = new ThaiHomePage();
    this.loginUsers = new String[][] { new String[] { "user0", "password" }, new String[] { "userfake", "passfake" },
    new String[] { "waiter", "waiter" } };
  }

  @Override
  public void tearDown() {
    // TASK Auto-generated method stub

  }

  @Test
  public void loginUser() {

    ThaiLoginPage loginPage = this.myThaiStarHome.clickLogInButton();

    loginPage.enterCredentials(this.loginUsers[1][0], this.loginUsers[1][1]);
    Assert.assertFalse("Usuario logeado", this.myThaiStarHome.isUserLogged(this.loginUsers[1][0]));
    loginPage = this.myThaiStarHome.clickLogInButton();

    loginPage.enterCredentials(this.loginUsers[0][0], this.loginUsers[0][1]);
    Assert.assertTrue("Usuario no logeado", this.myThaiStarHome.isUserLogged(this.loginUsers[0][0]));
  }

  @Test
  public void orderMenu() {

    ThaiMenuPage menuPage = this.myThaiStarHome.clickMenuButton();
    ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
    summaryPage.orderMenu(this.bookingId);
  }
}
