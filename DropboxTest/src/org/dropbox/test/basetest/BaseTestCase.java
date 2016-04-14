package org.dropbox.test.basetest;

import java.util.concurrent.TimeUnit;

import org.dropbox.page.dropbox.DropboxMainPage;
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
	
	public DropboxMainPage dropboxMainPage;
	
	public void initWebPages(){
		dropboxMainPage 		= new DropboxMainPage(driver);
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		System.out.println("...in Before");
		getDriver();
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
	public WebDriver getDriver() throws Exception{
		String browser = Utils.getIniFileValue("browser.type", "/resources/config/globalConfig.ini");
		
		String os = System.getProperty("os.name").toLowerCase();
		
		switch (browser){
		case FIREFOX : 
			driver =  new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
			
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
			return driver;
		}
		
		return null;
			
	}
	
}
