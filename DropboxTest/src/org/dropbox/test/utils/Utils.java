package org.dropbox.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;

/**
 * The Class Utils. Holds the static methods to work with Property files, and
 * retrieves the value based on appropriate key.
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
	
	public static String getGlobalConfigValue(String key){
		return getIniFileValue(key, "/resources/config/globalConfig.ini");
	}
	
	public static void waitFor(int millisec) throws InterruptedException {
		Thread.sleep(millisec);
	}
	
	public static boolean uploadFile(String file, String dropboxFileName) throws InterruptedException, DbxException, FileNotFoundException, IOException {
		DbxClientV2 client = getClient();
        
        try (InputStream in = new FileInputStream(file)) {
            client.files().uploadBuilder("/" + dropboxFileName).uploadAndFinish(in);
        }
        		
		return true;
	}
	
//	public static void getFiles(){
//		DbxClientV2 client = getClient();
//		client.files().search(path, query);
//	}

	private static DbxClientV2 getClient() {
		String accessToken = getGlobalConfigValue("dropbox.access.token");
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, accessToken);
		return client;
	}

}
