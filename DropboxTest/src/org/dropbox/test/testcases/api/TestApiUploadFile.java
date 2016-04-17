package org.dropbox.test.testcases.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dropbox.core.DbxException;


public class TestApiUploadFile extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestApiUploadFile.ini";
	
	/**
	 * Overrides the setUp method so no browsers are start by webdriver
	 * 
	 * @see org.dropbox.test.basetest.BaseTestCase#setUp()
	 */
	@BeforeMethod
	public void setUp(){}
	
	/**
	 * Overrides the tearDown method so no browsers are closed as they are not
	 * started
	 * 
	 * @see org.dropbox.test.basetest.BaseTestCase#tearDown()
	 */
	@AfterMethod
	public void tearDown(){}
	
	@Test
	public void testApiUploadFile() throws InterruptedException, DbxException, FileNotFoundException, IOException {		
		System.out.println("-->in test method: " + getClass().getName());
				
		String textFile = Utils.getAbsolutePath() + Utils.getIniFileValue("upload.text.file", iniFile);
		String newFileName = Utils.getIniFileValue("file.new.name", iniFile);
		System.out.println("...text file: " + textFile);
		System.out.println("...new name: " + newFileName);
		Utils.uploadFile(textFile, newFileName);
	}	
}
