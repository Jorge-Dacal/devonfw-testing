package com.example.selenium.tests.tests.googleSearch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.example.selenium.pages.googleSearch.GoogleResultPage;
import com.example.selenium.pages.googleSearch.GoogleSearchPage;

public class GoogleSearchTest extends BaseTest {
	private GoogleSearchPage googleSearchPage;
	
	@Override
	public void setUp() {
		googleSearchPage = new GoogleSearchPage();
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void shouldResultReturn() {
		GoogleResultPage googleResultPage = googleSearchPage.enterGoogleSearchInput("Test");
		ListElements results = googleResultPage.getResultList();
		assertTrue("Number of results equals 0", results.getSize() > 0);
	}
}