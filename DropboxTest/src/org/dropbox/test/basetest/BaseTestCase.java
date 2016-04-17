package org.dropbox.test.basetest;

import java.util.concurrent.TimeUnit;

import org.dropbox.page.dropbox.DropboxHomePage;
import org.dropbox.page.dropbox.DropboxLoginPage;
import org.dropbox.test.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestCase {

	private WebDriver driver;
	
	private final String CHROME = "chrome";
	private final String FIREFOX = "ff";
	
	public DropboxLoginPage dropboxLoginPage;
	public DropboxHomePage dropboxHomePage;
	
	/**
	 * Create the instances of the webpages for working with them.
	 */
	public void initWebPages(){
		dropboxLoginPage 		= new DropboxLoginPage(driver);
		dropboxHomePage			= new DropboxHomePage(driver);
	}
	
	/**
	 * This methods executes before each test method. Prepared WebDirve and
	 * WebPsges to work with them.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		System.out.println("==>in Before");
		initDriver();
		initWebPages();
	}

	/**
	 * This method executes after each test method and closes all WebDriver
	 * windows.
	 */
	@AfterMethod
	public void tearDown() {
		System.out.println("==>in After");
		quit();
	}
	
	/**
	 * Quit all open Webdrivers windows.
	 */
	public void quit(){
		driver.quit();
	}
	
	/**
	 * Close current Webdriver window
	 */
	public void close(){
		driver.close();
	}
	
	/**
	 * Inits the Webdriver and sets the value of implicitly wait seconds.
	 *
	 * @return the Webdriver
	 * @throws Exception 
	 */
	public WebDriver initDriver() throws Exception{
		String browser = Utils.getIniFileValue("browser.type", "/resources/config/globalConfig.ini");
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("...OS: " + os);
		driver = null;
		
		switch (browser){
		
		case FIREFOX : 
			driver =  new FirefoxDriver();
			break;
			
		case CHROME :
			//sets the driver executable depending on current OS		
			if (os.contains("lin")){
				System.setProperty("webdriver.chrome.driver", Utils.getAbsolutePath() + "/resources/driver/lin/chromedriver");
			} else if (os.contains("win")){			
				System.setProperty("webdriver.chrome.driver", Utils.getAbsolutePath() + "/resources/driver/win/chromedriver.exe");
			} else {
				throw new Exception("Chromedriver is not specified for this OS!!!");
			}
			
			driver = new ChromeDriver();
			break;
		}
		
		int implWait = Integer.parseInt(Utils.getGlobalConfigValue("driver.implicitly.wait"));
		driver.manage().timeouts().implicitlyWait(implWait, TimeUnit.SECONDS);
		return driver;			
	}
	
	/**
	 * Login to Dropbox using user credentials provided in testng.xml file
	 *
	 * @param loginName
	 *            the login name
	 * @param password
	 *            the password
	 */
	public void login(String loginName, String password) {
		dropboxLoginPage.openDropbox();
		if (!dropboxLoginPage.isLogoPresent()){
			Assert.fail("Dropbox logo is not found!!!");
		}
		dropboxLoginPage.signIn(loginName, password);
		if (!dropboxHomePage.isHomePage()) {
			Assert.fail("Not logged to Dropbox!!!");
		}
	}
	
	/**
	 * Logout from the Dropbox Home page.
	 */
	public void logout() {
		dropboxHomePage.signOut();
		if (!dropboxLoginPage.isLogoPresent()){
			Assert.fail("Logout failed!!!");
		}
	}
	
	/**
	 * Delete file by its name from the root Dropbox folder
	 *
	 * @param fileName the file name
	 */
	public void deleteFile(String fileName) {
		if (dropboxHomePage.isFileExist(fileName)) {
			System.out.println("...found file, deleting");
			dropboxHomePage.deleteFile(fileName);
		} else {
			System.out.println("...No files found");
		}
	}
	
}
