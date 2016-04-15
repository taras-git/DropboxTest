package org.dropbox.test.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dropbox.core.DbxException;


public class TestUploadFile extends BaseTestCase {
	
	/** The ini file, with values for this testcase */
	String iniFile = "/resources/data/testcases/TestUploadFile.ini";

	@BeforeMethod
	public void setUp(){}
	
	@AfterMethod
	public void tearDown(){}
	
	
	@Test
	public void testUploadFile() throws InterruptedException, DbxException, FileNotFoundException, IOException {		
		System.out.println("-->in test method: " + getClass().getName());
				
		String textFile = Utils.getAbsolutePath() + Utils.getIniFileValue("upload.text.file", iniFile);
		String newFileName = Utils.getIniFileValue("file.new.name", iniFile);
		System.out.println("...text file: " + textFile);
		System.out.println("...new name: " + newFileName);
		Utils.uploadFile(textFile, newFileName);
		
	}	
	
}
