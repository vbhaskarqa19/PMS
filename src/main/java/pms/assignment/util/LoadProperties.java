package pms.assignment.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class LoadProperties {
	
	/** The config. */
	private static Properties CONFIG = null;

	/**
	 * Gets the config - Returns CONFIG file as properties object.
	 *
	 * @param fileName
	 *            the file name
	 * @return the config
	 */
	public static Properties getConfig(String fileName) {

		synchronized (LoadProperties.class) {
			if (null != CONFIG) {
				return CONFIG;
			}

			String filePath = Constants.PROPERTIES_URI + fileName;
			FileInputStream f;
			try {
				f = new FileInputStream(filePath);
				CONFIG = new Properties();
				CONFIG.load(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return CONFIG;
		}
	}
}
