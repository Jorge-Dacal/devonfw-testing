/**
 * 
 */
package com.example.selenium.pages.mythaistar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;

/**
 * @author jambulud
 */
public class ThaiHomePage extends BasePage {
	
	private static final By	loginButtonSearch	= By.xpath("//button[@class=\"mat-icon-button ng-star-inserted\"]");
	private static final By	labelLoginSearch	= By.xpath("//span[@class='forDesktop']");
	private static final By	menuTabSearch		= By.xpath("//a[@routerlink='/menu']");
	
	@Override
	public boolean isLoaded() {
		if (getDriver().getTitle()
				.equals(pageTitle())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void load() {
		getDriver().get("http://mts-angular-my-thai-star-mrcheck.10.36.39.36.nip.io/");
	}
	
	@Override
	public String pageTitle() {
		return "My Thai Star";
	}
	
	public ThaiLoginPage clickLogInButton() {
		WebElement loginButton = getDriver().findElementDynamic(loginButtonSearch);
		loginButton.click();
		
		return new ThaiLoginPage();
	}
	
	public ThaiMenuPage clickMenuButton() {
		WebElement menuTab = getDriver().findElementDynamic(menuTabSearch);
		menuTab.click();
		
		return new ThaiMenuPage();
	}
	
	public boolean isUserLogged(String username) {
		try {
			List<WebElement> accessButton = getDriver().findElementDynamics(labelLoginSearch);
			System.out.println("Usuario: " + accessButton.get(0)
					.getText());
			if (accessButton.size() > 0 && accessButton.get(0)
					.getText()
					.equals(username)) {
				System.out.println("Usuario: " + accessButton.get(0)
						.getText());
				return true;
			}
		} catch (BFElementNotFoundException e) {
			System.out.println("Usuarioss:" + 0);
			return false;
		}
		
		System.out.println("Usuarioss:" + 0);
		return false;
	}
	
}
