package org.dropbox.page.dropbox;

import org.openqa.selenium.WebDriver;

import org.dropbox.page.basepage.BasePage;

public class DropboxMainPage extends BasePage {

	public DropboxMainPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Checks if logo is present on the web page.
	 *
	 * @return true, if logo is present
	 */
	public boolean isLogoPresent() {
		String xpath = ".//*";
		return isElementPresentByXpath(xpath);
	}
	
	/**
	 * Maximize the browser window, set url to www.aol.com and open this url
	 */
	public void openDropbox(){
		maximizeBrowserScreen();
		driver.get("https://www.dropbox.com");	
	}

}
