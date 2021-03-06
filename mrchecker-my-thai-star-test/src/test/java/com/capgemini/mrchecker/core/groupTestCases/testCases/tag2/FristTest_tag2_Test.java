package com.capgemini.mrchecker.core.groupTestCases.testCases.tag2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSlow;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsTag2;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Feature;

@Feature("TAG2")
@Category({ TestsTag2.class, TestsSlow.class, TestsIE.class })
public class FristTest_tag2_Test extends BaseTest {
	
	private RegistryPage registryPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
		
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void QCID_StayOnResistryPage_Tag2_First() throws InterruptedException {
		
		BFLogger.logInfo(
				"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(true, is(registryPage.isLoaded()));
		
		TimeUnit.SECONDS.sleep(3); // This is for demo. Do not do it at home
	}
	
	@Test
	public void testTag2_Frist() {
		BFLogger.logInfo("FristTest_tag2.test()");
		
	}
	
}
