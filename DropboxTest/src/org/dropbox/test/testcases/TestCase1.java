package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.testng.annotations.Test;


public class TestCase1 extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestCase1.ini";

	@Test
	public void testCase1() throws InterruptedException {
		System.out.println("...in test method");
		dropboxMainPage.openDropbox();
		dropboxMainPage.isLogoPresent();
	}

}
