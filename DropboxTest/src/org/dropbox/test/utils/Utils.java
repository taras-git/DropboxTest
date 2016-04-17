package org.dropbox.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

/**
 * The Class Utils.
 * 
 * Holds the static methods to work with Property files, and retrieves the value
 * based on appropriate key.
 * 
 * Holds the static methods to work with DropBox API calls
 * 
 *
 * @author Taras Tymchyshyn
 */
public class Utils {

	/**
	 * Gets the absolute path of the working directory.
	 *
	 * @return the absolute path
	 */
	public static Path getAbsolutePath() {
		return Paths.get("").toAbsolutePath();
	}

	/**
	 * Gets the value from *.ini file, based on appropriate key.
	 *
	 * @param key
	 *            the key
	 * @param path
	 *            the path to the ini file
	 * @return the value
	 */
	public static String getIniFileValue(String key, String path) {
		Properties prop = new Properties();
		InputStream input = null;
		String value = null;

		try {

			input = new FileInputStream(getAbsolutePath() + path);
			// load a properties file
			prop.load(input);
			// get the property value
			value = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// return the value in case of success
		return value;
	}
	
	/**
	 * Gets the value from global config file globalConfig.ini.
	 *
	 * @param key
	 *            the key
	 * @return the global config value
	 */
	public static String getGlobalConfigValue(String key){
		return getIniFileValue(key, "/resources/config/globalConfig.ini");
	}
	
	/**
	 * Sleep the current thread execution.
	 *
	 * @param millisec
	 *            the millisec to sleep
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void waitFor(int millisec) throws InterruptedException {
		Thread.sleep(millisec);
	}
	
	/**
	 * Upload the file to Dropbox root folder using Dropbox API call.
	 *
	 * @param file
	 *            the file to be uploaded
	 * @param dropboxFileName
	 *            the file name after it is uploaded to Dropbox
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws DbxException
	 *             the dbx exception
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean uploadFile(String file, String dropboxFileName) throws InterruptedException, DbxException, FileNotFoundException, IOException {
		DbxClientV2 client = getClient();
		
        try (InputStream in = new FileInputStream(file)) {
            client.files().uploadBuilder("/" + dropboxFileName).uploadAndFinish(in);
        }        
        		
		return true;
	}
	
	/**
	 * Gets the items (folders and files) from the Dropbox root folder.
	 *
	 * @return the items
	 * @throws ListFolderErrorException
	 *             the list folder error exception
	 * @throws DbxException
	 *             the dbx exception
	 */
	public static ListFolderResult getItems() throws ListFolderErrorException, DbxException{
		DbxClientV2 client = getClient();
		ListFolderResult result = getClient().files().listFolder("");
		
        while (true) {
            if (!result.getHasMore()) {
                break;
            }
            result = client.files().listFolderContinue(result.getCursor());
        }

        return result;
	}
	
	/**
	 * Creates list of Dropbox items. Goes recursevely thru all folders in
	 * Dropbox root folder and adds its absolute path to the list.
	 *
	 * @return the array list
	 * @throws ListFolderErrorException
	 *             the list folder error exception
	 * @throws DbxException
	 *             the dbx exception
	 */
	public static ArrayList<String> listItems() throws ListFolderErrorException, DbxException {
		ArrayList<String> list = new ArrayList<String>();
		return listItems("", list);
	}
	
	private static ArrayList<String> listItems(String folder, ArrayList<String> list) throws ListFolderErrorException, DbxException {
		DbxClientV2 client = getClient();
		ListFolderResult result = client.files().listFolder(folder);

		while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println("...found item: " + metadata.getPathDisplay() + " -> " + metadata.getClass());
				list.add(metadata.getPathDisplay());
				if (metadata instanceof FolderMetadata) {
					listItems(metadata.getPathDisplay(), list);
				}
			}
			if (!result.getHasMore()) {
				break;
			}
			result = client.files().listFolderContinue(result.getCursor());
		}
		return list;
	}
	
	/**
	 * Clean up the Dropbox root folder from all custom folders and uploaded
	 * files.
	 *
	 * @throws ListFolderErrorException
	 *             the list folder error exception
	 * @throws DbxException
	 *             the dbx exception
	 */
	public static void cleanUpDropbox() throws ListFolderErrorException, DbxException{
		DbxClientV2 client = getClient();
		ListFolderResult result = getItems();
		for (Metadata item : result.getEntries()) {
			client.files().delete(item.getPathDisplay());
			System.out.println("...deleted: " + item.getName());
		}
	}

	/**
	 * Gets the Dropbox client to perform different API Dropbox calls.
	 *
	 * @return the Dropbox client
	 */
	private static DbxClientV2 getClient() {
		String accessToken = getGlobalConfigValue("dropbox.access.token");
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		DbxClientV2 client = new DbxClientV2(config, accessToken);
		return client;
	}

}
