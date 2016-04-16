package org.dropbox.test.testcases.ui;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestUiUploadedFile extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestUploadFile.ini";

	@Test
	@Parameters({"loginName", "password"})
	public void testUploadedFile(String loginName, String password) throws InterruptedException {
		System.out.println("-->in test method: " + getClass().getName());
		
		login(loginName, password);
		
		String fileName = Utils.getIniFileValue("file.new.name", iniFile);
		if (!dropboxHomePage.isFilePresent(fileName)) {
			Assert.fail("File: " + fileName + " is not found!!!");
		} else {
			System.out.println("...File found: " + fileName);
		}
		
		logout();
	}
	

}
