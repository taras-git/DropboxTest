package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestLoginLogout extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestLoginLogout.ini";

	@Test
	@Parameters({"loginName", "password"})
	public void testLoginLogout(String loginName, String password) throws InterruptedException {
		System.out.println("...in test method");
		
		login(loginName, password);
		deleteFile(Utils.getIniFileValue("file.name", iniFile));
		logout();
	}


}
