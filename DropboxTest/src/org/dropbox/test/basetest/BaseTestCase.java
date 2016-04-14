package org.dropbox.test.basetest;

import java.util.concurrent.TimeUnit;

import org.dropbox.page.dropbox.DropboxHomePage;
import org.dropbox.page.dropbox.DropboxLoginPage;
import org.dropbox.test.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestCase {

	private WebDriver driver;
	
	private final String CHROME = "chrome";
	private final String FIREFOX = "ff";
	
	public DropboxLoginPage dropboxLoginPage;
	public DropboxHomePage dropboxHomePage;
	
	public void initWebPages(){
		dropboxLoginPage 		= new DropboxLoginPage(driver);
		dropboxHomePage			= new DropboxHomePage(driver);
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		System.out.println("...in Before");
		initDriver();
		initWebPages();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("...in After");
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
	 * Inits the Firefox Webdriver and sets the value of implicitly wait seconds.
	 *
	 * @return the Webdriver
	 * @throws Exception 
	 */
	public WebDriver initDriver() throws Exception{
		String browser = Utils.getIniFileValue("browser.type", "/resources/config/globalConfig.ini");
		String os = System.getProperty("os.name").toLowerCase();
		driver = null;
		
		switch (browser){
		
		case FIREFOX : 
			driver =  new FirefoxDriver();
			break;
			
		case CHROME :
			System.out.println("...OS: " + os);
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
	
}
