package com.sneaklife.ut.code.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	/**
	 * 系统配置文件
	 */
	private static final String SYSTEM_FILE = "system.properties";
	/**
	 * 系统配置文件对象
	 */
	private static Properties systemProperties = new Properties();
	static{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = null;
		try {
			inputStream = classLoader.getResourceAsStream(SYSTEM_FILE);
			systemProperties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取系统配置值
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param name 配置项
	 * @return String
	 */
	public static String getSystemConfig(String name) {
		String value = "";
		try {
			value = ConfigProperties.systemProperties.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
