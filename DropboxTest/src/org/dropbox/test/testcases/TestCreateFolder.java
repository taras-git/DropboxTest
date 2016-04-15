package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestCreateFolder extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestCreateFolder.ini";

	@Test
	@Parameters({"loginName", "password"})
	public void testCreateFolder(String loginName, String password) {
		System.out.println("-->in test method: " + getClass().getName());
		
		login(loginName, password);	
		String folderName = Utils.getIniFileValue("folder.name", iniFile);
		dropboxHomePage.createNewFolder(folderName);
		logout();
	}	
}
