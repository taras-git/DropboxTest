package org.dropbox.test.testcases.api;

import java.util.ArrayList;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;


public class TestApiMovedFile extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFileFileName = "/resources/data/testcases/TestApiUploadFile.ini";
	String iniFileFolder = "/resources/data/testcases/TestUiCreateFolder.ini";
	
	String fileName = Utils.getIniFileValue("file.new.name", iniFileFileName);
	String folderName = Utils.getIniFileValue("folder.name", iniFileFolder);
	
	@BeforeMethod
	public void setUp(){}
	
	@AfterMethod
	public void tearDown(){}
	
	@Test
	public void testApiMoveFile() throws ListFolderErrorException, DbxException  {
		System.out.println("-->in test method: " + getClass().getName());

		String movedFile = "/" + folderName + "/" + fileName;
		ArrayList<String> items = Utils.listItems();
		if (!items.contains(movedFile)) {
			Assert.fail("File not moved in the list, expected: " + movedFile);
		} else {
			System.out.println("...OK - found moved file");
		}
	}
}
