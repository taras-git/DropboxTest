package org.dropbox.test.testcases.ui;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestUiMoveFile extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFileFileName = "/resources/data/testcases/TestApiUploadFile.ini";
	String iniFileFolder = "/resources/data/testcases/TestUiCreateFolder.ini";
	
	String fileName = Utils.getIniFileValue("file.new.name", iniFileFileName);
	String folderName = Utils.getIniFileValue("folder.name", iniFileFolder);

	@Test
	@Parameters({"loginName", "password"})
	public void testUiMoveFile(String loginName, String password) throws InterruptedException {
		System.out.println("-->in test method: " + getClass().getName());
		
		login(loginName, password);
		
		if (!dropboxHomePage.isFileExist(fileName)){
			Assert.fail("File: " + fileName + " is not found!!!");
		}
		
		dropboxHomePage.moveFile(fileName, folderName);
		
		logout();
	}
	

}
