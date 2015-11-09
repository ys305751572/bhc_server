package com.gcs.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoad {
	public synchronized static String getPValue(String key, String fileName) {
		String pValue = "";
		try {
				Properties p = new Properties();
				 InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);  
				 p.load(stream);
				pValue = p.getProperty(key);
				stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pValue;
	}
}
