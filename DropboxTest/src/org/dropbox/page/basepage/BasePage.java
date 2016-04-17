package org.dropbox.page.basepage;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {

	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the current web page title.
	 *
	 * @return the title
	 */
	public String getActualTitle() {
		return driver.getTitle();
	}

	/**
	 * Maximize browser screen.
	 */
	public void maximizeBrowserScreen() {
		driver.manage().window().maximize();
	}

	/**
	 * Checks if element can be found on the web page. The webpage is checked
	 * every 500 milliseconds until the element is found or timeout is reached
	 * Timeout is set in ini file as amount of seconds to implicitly wait for an
	 * element
	 *
	 * @param locator
	 *            the locator
	 * @return true, if element is present
	 */
	public boolean isElementPresent(By locator) {
		List<WebElement> webElements = driver.findElements(locator);
		if (webElements.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if element can be found by its id on the web page. The webpage is
	 * checked every 500 milliseconds until the element is found or timeout is
	 * reached Timeout is set in ini file as amount of seconds to implicitly
	 * wait for an element
	 *
	 * @param id
	 *            the id of the webelement
	 * @return true, if element is found
	 */
	public boolean isElementPresentById(String id) {
		return isElementPresent(By.id(id));
	}

	/**
	 * Checks if element can be found by its xpath on the web page. The webpage
	 * is checked every 500 milliseconds until the element is found or timeout
	 * is reached Timeout is set in ini file as amount of seconds to implicitly
	 * wait for an element
	 *
	 * @param xpath
	 *            the xpath of the element
	 * @return true, if element is found
	 */
	public boolean isElementPresentByXpath(String xpath) {
		return isElementPresent(By.xpath(xpath));
	}

	
	/**
	 * Wait for the webelement and if it is found, send the text to webelent.
	 * 
	 *
	 * @param text
	 *            the text to be sent to the webelement
	 * @param locator
	 *            the locator of the webelement
	 * @return true, if successful
	 */
	public boolean sendText(String text, By locator) {
		if (isElementPresent(locator)) {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
			return true;
		}
		return false;
	}

	/**
	 * Find the element and click on it.
	 *
	 * @param locator
	 *            the locator of the webelement.
	 */
	public boolean clickElement(By locator) {
		if (isElementPresent(locator)) {
			driver.findElement(locator).click();
			return true;
		}
		return false;
	}

	/**
	 * Open link in new tab of the current window, and switch the focus to it
	 *
	 * @param locator
	 *            the locator of the link to be clicked
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void openLinkInNewTab(By locator) throws InterruptedException {
		WebElement link = driver.findElement(locator);
		Actions newTab = new Actions(driver);
		newTab.keyDown(Keys.CONTROL)
				.keyDown(Keys.SHIFT)
				.click(link)
				.keyUp(Keys.CONTROL)
				.keyUp(Keys.SHIFT)
				.build()
				.perform();
		Set<String> windowsSet = driver.getWindowHandles();
		driver.switchTo().window((String) windowsSet.toArray()[0]);
	}
	
	/**
	 * Performs the right button mouse click on web element.
	 *
	 * @param element the element
	 */
	public void rightClickOnWebElement(WebElement element) {
		Actions action= new Actions(driver);
		action.contextClick(element)
					.build()
					.perform();
	}
}
