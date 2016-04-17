package org.dropbox.test.testcases;

import org.dropbox.test.basetest.BaseTestCase;
import org.dropbox.test.utils.Utils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;


public class DropboxCleanUp extends BaseTestCase {

	/**
	 * This method executes before and after each test run. Deletes all files
	 * and custom folders from the root Dropbox folder.
	 *
	 * @throws ListFolderErrorException
	 *             the list folder error exception
	 * @throws DbxException
	 *             the dbx exception
	 */
	@BeforeSuite
	@AfterSuite
	public void dropboxCleanUp() throws ListFolderErrorException, DbxException {
		System.out.println("-->in test method: " + getClass().getName());
		Utils.listItems();
		Utils.cleanUpDropbox();
	}
}