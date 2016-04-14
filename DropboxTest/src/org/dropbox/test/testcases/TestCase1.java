package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestCase1 extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestCase1.ini";

	@Test
	@Parameters({"loginName", "password"})
	public void testCase1(String loginName, String password) throws InterruptedException {
		System.out.println("...in test method");
		
		dropboxLoginPage.openDropbox();
		if (!dropboxLoginPage.isLogoPresent()){
			Assert.fail("Dropbox logo is not found!!!");
		}
		dropboxLoginPage.signIn(loginName, password);
		
		if (!dropboxHomePage.isHomePage()) {
			Assert.fail("Not logged to Dropbox!!!");
		}
		
		dropboxHomePage.signOut();
		
		if (!dropboxLoginPage.isLogoPresent()){
			Assert.fail("Logout failed!!!");
		}
		
	}

}
