package org.dropbox.page.dropbox;

import org.dropbox.page.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropboxHomePage extends BasePage {

	public DropboxHomePage(WebDriver driver) {
		super(driver);
	}
	
	String feedLinkXpath = ".//*[@id='notification-feed-nav-link']/span/button";
	String userAccountButtonXpath = ".//*[@id='header-account-menu']/span/button";
	String signOutLinkXpath = ".//a[contains(text(), 'Sign out') and @class='standalone clearfix' and @href='/logout']";
	
	public boolean isHomePage(){
		if (isElementPresent(By.xpath(feedLinkXpath))) {
			return true;
		}
		return false;
	}

	public boolean signOut() {
		clickElement(By.xpath(userAccountButtonXpath));
		return clickElement(By.xpath(signOutLinkXpath));		
	}

}
