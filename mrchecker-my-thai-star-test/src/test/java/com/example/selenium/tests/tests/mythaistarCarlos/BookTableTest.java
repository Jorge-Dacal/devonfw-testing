package com.example.selenium.tests.tests.mythaistarCarlos;

import org.junit.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.pages.mythaistarCarlos.ThaiBookPage;
import com.example.selenium.pages.mythaistarCarlos.ThaiConfirmBookPage;
import com.example.selenium.pages.mythaistarCarlos.ThaiDateTimePage;
import com.example.selenium.pages.mythaistarCarlos.ThaiHomePage;

public class BookTableTest extends BaseTest {
	
	private String[]		bookingData;
	private ThaiHomePage	myThaiStarHome;
	
	@Override
	public void setUp() {
		myThaiStarHome = new ThaiHomePage();
		bookingData = new String[] { "Jackie Chan", "kungfu@fakemail.com", "8" };
	}
	
	@Override
	public void tearDown() {
		// TASK Auto-generated method stub
		
	}
	
	@Test
	public void shouldResultReturn() {
		ThaiBookPage myBookPage = myThaiStarHome.clickBookTable();
		if (myBookPage != null) {
			System.out.println("NO Es nulo");
		}
		// bookPage.enterCredentials(loginUsers[0][0], loginUsers[0][1]);
		
		ThaiDateTimePage myDateTimePage = myBookPage.enterTimeAndDate();
		myDateTimePage.setUpDateAndTime();
		
		ThaiConfirmBookPage myComfirmPage = myBookPage.enterBookingData(bookingData[0], bookingData[1], bookingData[2]);
		myComfirmPage.confirmBookingData();
		
		myBookPage.checkConfirmationDialog();
		
	}
	
}
