package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;


public class DropboxCleanUp extends BaseTestCase {

	@BeforeSuite
	@AfterSuite
	public void dropboxCleanUp() throws ListFolderErrorException, DbxException {
		System.out.println("-->in test method: " + getClass().getName());
				
		Utils.getItems(true);
		Utils.cleanUpDropbox();
	}
}
