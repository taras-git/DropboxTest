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
	
	
	/**
	 * Checks if user is logged to Dropbox, and the home page is opened.
	 *
	 * @return true, if home page is opened
	 */
	public boolean isHomePage(){
		if (isElementPresent(feedLinkXpath)) {
			return true;
		}
		return false;
	}

	/**
	 * Choose "Sign out" option from the menu and click on it.
	 *
	 * @return true, if successful
	 */
	public boolean signOut() {
		clickElement(userAccountButtonXpath);
		return clickElement(signOutLinkXpath);		
	}

	/**
	 * Checks if file name is displayed on Dropbox home page.
	 *
	 * @param fileName
	 *            the file name
	 * @return true, if is file exist
	 */
	public boolean isFileExist(String fileName) {
		By fileNameXpath = By.xpath(".//a[.='" 
				+ fileName 
				+ "']");
		if (isElementPresent(fileNameXpath)) {
			return true;
		}
		return false;
		
	}

	/**
	 * Delete file by clicking with right mouse button on it and choosing Delete
	 * option.
	 *
	 * @param fileName
	 *            the file name
	 */
	public void deleteFile(String fileName) {
		By fileNameXpath = By.xpath(".//a[.='" 
				+ fileName 
				+ "']");
		WebElement file = driver.findElement(fileNameXpath);
		rightClickOnWebElement(file);
		driver.findElement(deleteOptionMenu).click();
		driver.findElement(deleteConfirmDialog).click();
	}
	
	
	/**
	 * Move file by clicking right mouse button on it and choosing Move option.
	 * After click on desired folder to move.
	 *
	 * @param fileName
	 *            the file name
	 * @param folderName
	 *            the folder name
	 */
	public void moveFile(String fileName, String folderName) {
		By fileNameXpath = By.xpath(".//a[.='" 
				+ fileName 
				+ "']");
		By folderNameXpath = By.xpath(".//a[.='"
				+ folderName
				+ "']/img");
		WebElement file = driver.findElement(fileNameXpath);
		rightClickOnWebElement(file);
		clickElement(moveOptionMenu);
		clickElement(folderNameXpath);
		clickElement(moveConfirmDialog);

	}

	/**
	 * Creates new folder by clicking New Folder icon and sends the new folder
	 * name to the field.
	 *
	 * @param folderName
	 *            the folder name
	 * @return true, if successful
	 */
	public boolean createNewFolder(String folderName) {
		if (isElementPresent(createNewFolderButton)){
			driver.findElement(createNewFolderButton).click();
			driver.findElement(folderNameInput).sendKeys(folderName);
			return true;
		}
		return false;
		
	}

	/**
	 * Checks if folder name is displayed on Dropbox home page.
	 *
	 * @param folderName
	 *            the folder name
	 * @return true, if is folder exists
	 */
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
