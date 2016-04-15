package org.dropbox.page.dropbox;

import org.dropbox.page.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DropboxHomePage extends BasePage {

	public DropboxHomePage(WebDriver driver) {
		super(driver);
	}
	
	By feedLinkXpath = By.xpath(".//*[@id='notification-feed-nav-link']/span/button");
	By userAccountButtonXpath = By.xpath(".//*[@id='header-account-menu']/span/button");
	By signOutLinkXpath = By.xpath(".//a[contains(text(), 'Sign out')"
			+ " and @class='standalone clearfix'"
			+ " and @href='/logout']");	
	By deleteOptionMenu = By.xpath(".//button[@id='delete_button']");
	By deleteConfirmDialog = By.xpath(".//button[.='Delete']");
	
	public boolean isHomePage(){
		if (isElementPresent(feedLinkXpath)) {
			return true;
		}
		return false;
	}

	public boolean signOut() {
		clickElement(userAccountButtonXpath);
		return clickElement(signOutLinkXpath);		
	}

	public boolean isFilePresent(String fileName) {
		String fileNameXpath = ".//a[.='" + fileName + "']";
		if (isElementPresentByXpath(fileNameXpath)) {
			return true;
		}
		return false;
		
	}

	public void deleteFile(String fileName) {
		By fileNameXpath = By.xpath(".//a[.='" + fileName + "']");
		WebElement file = driver.findElement(fileNameXpath);
		
		Actions action= new Actions(driver);
		action.contextClick(file)
					.build()
					.perform();

		driver.findElement(deleteOptionMenu).click();
		driver.findElement(deleteConfirmDialog).click();
	}
	

}
