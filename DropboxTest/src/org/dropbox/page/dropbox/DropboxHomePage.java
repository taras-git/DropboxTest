package org.dropbox.page.dropbox;

import org.dropbox.page.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	By moveOptionMenu = By.xpath(".//button[@id='move_button']");
	By moveConfirmDialog = By.xpath(".//input[@value='Move']");
	By createNewFolderButton = By.xpath(".//a[@id='new_folder_button']/img");
	By folderNameInput = By.xpath(".//*[@id='null']/input");
	
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

	public boolean isFileExist(String fileName) {
		By fileNameXpath = By.xpath(".//a[.='" + fileName + "']");
		if (isElementPresent(fileNameXpath)) {
			return true;
		}
		return false;
		
	}

	public void deleteFile(String fileName) {
		By fileNameXpath = By.xpath(".//a[.='" + fileName + "']");
		WebElement file = driver.findElement(fileNameXpath);
		rightClickOnWebElement(file);
		driver.findElement(deleteOptionMenu).click();
		driver.findElement(deleteConfirmDialog).click();
	}
	
	public void moveFile(String fileName, String folderName) {
		By fileNameXpath = By.xpath(".//a[.='" + fileName + "']");
		By folderNameXpath = By.xpath(".//a[.='"
				+ folderName
				+ "']/img");
		WebElement file = driver.findElement(fileNameXpath);
		rightClickOnWebElement(file);
		clickElement(moveOptionMenu);
		clickElement(folderNameXpath);
		clickElement(moveConfirmDialog);

	}

	public boolean createNewFolder(String folderName) {
		if (isElementPresent(createNewFolderButton)){
			driver.findElement(createNewFolderButton).click();
			driver.findElement(folderNameInput).sendKeys(folderName);
			return true;
		}
		return false;
		
	}

	public boolean isFolderExists(String folderName) {
		By folderNameXpath = By.xpath(".//a[.='"
				+ folderName
				+ "']");
		if (isElementPresent(folderNameXpath)) {
			return true;
		}
		return false;
	}
	

}
