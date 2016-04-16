package org.dropbox.test.testcases.ui;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestUiCreateFolder extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestUiCreateFolder.ini";
	String folderName = Utils.getIniFileValue("folder.name", iniFile);

	@Test
	@Parameters({"loginName", "password"})
	public void testCreateFolder(String loginName, String password) {
		System.out.println("-->in test method: " + getClass().getName());
		
		login(loginName, password);	
		dropboxHomePage.createNewFolder(folderName);
		logout();
	}	
	
	@Test(dependsOnMethods="testCreateFolder")
	@Parameters({"loginName", "password"})
	public void testVerifyFolderCreated(String loginName, String password) {
		System.out.println("-->in test method: " + getClass().getName());
		
		login(loginName, password);	
		if (!dropboxHomePage.isFolderExists(folderName)){
			Assert.fail("Folder " + folderName + " is not found!!!");
		}
		logout();
	}	
	
}
