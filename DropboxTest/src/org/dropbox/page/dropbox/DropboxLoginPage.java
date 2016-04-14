package org.dropbox.page.dropbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.dropbox.page.basepage.BasePage;
import org.dropbox.test.utils.Utils;

public class DropboxLoginPage extends BasePage {

	public DropboxLoginPage(WebDriver driver) {
		super(driver);
	}
	
	String signInXpath 			= ".//a[@id='sign-in']";
	String dropboxLogoXpath 	= ".//h1[@id='dropbox-logo']";
	String userEmailXpath 		= "(.//input[starts-with(@id,'pyxl')"
			+ " and @class='text-input-input autofocus'"
			+ " and @type='email'"
			+ " and @name='login_email'])[1]";
	String userPasswordXpath	= "(.//input[starts-with(@id,'pyxl')"
			+ " and @class='password-input text-input-input'"
			+ " and @type='password'"
			+ " and @name='login_password'])[1]";
	String signInButtonXpath 	= "(.//div[@class=\"sign-in-text\" and contains(text(),\"Sign in\")])[1]";


	/**
	 * Checks if logo is present on the web page.
	 *
	 * @return true, if logo is present
	 */
	public boolean isLogoPresent() {
		return isElementPresentByXpath(dropboxLogoXpath);
	}
	
	/**
	 * Maximize the browser window, set url to www.dropbox.com and open this url
	 */
	public void openDropbox(){
		maximizeBrowserScreen();
		String dropboxUrl = Utils.getGlobalConfigValue("dropbox.url");
		driver.get(dropboxUrl);	
	}
	
	public void clickSignIn(){
		driver.findElement(By.xpath(signInXpath)).click();
	}

	public void signIn(String userEmail, String password) {
		clickSignIn();
		sendText(userEmail, By.xpath(userEmailXpath));
		sendText(password, By.xpath(userPasswordXpath));
		clickElement(By.xpath(signInButtonXpath));
	}


}
