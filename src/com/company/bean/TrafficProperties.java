package com.company.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class holds the configuration properties
 * of the system. It has properties defined in 
 * traffic.properties file.
 * 
 * @author Taha
 *
 */
public class TrafficProperties {

	private static final Properties prop = new Properties();
	
	private static final TrafficProperties instance = new TrafficProperties();


	private TrafficProperties() {
		init();
	}

	public static TrafficProperties getInstance() {
		return instance;
	}

	/**
	 * This methos loads the property files into prop object.
	 */
	private void init() {
		InputStream input = null;

		try {
			input = this.getClass().getResourceAsStream("/traffic.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			//Log the Exception.
		} catch (IOException e) {
			//Log the Exception.
		}catch(Exception e) {
			//Log the Exception.
		}
	}
	
	/**
	 * Returns string properties.
	 * @param propertyName
	 * @return String
	 */
	public String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}
	
	/**
	 * Returns integer properties.
	 * @param propertyName
	 * @return int
	 */
	public int getIntProperty(String propertyName) {
		return Integer.valueOf(prop.getProperty(propertyName));
	}
}
