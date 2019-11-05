package com.sneaklife.util.code.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	/**
	 * 系统配置文件
	 */
	private static final String SYSTEM_FILE = "system.properties";
	/**
	 * 短信配置文件
	 */
	private static final String AIL_FILE = "ail.properties";
	/**
	 * 邮箱配置文件
	 */
	private static final String MAIL_FILE = "mail.properties";
	/**
	 * 系统配置文件对象
	 */
	private static Properties systemProperties = new Properties();
	/**
	 * 短信配置文件对象
	 */
	private static Properties aliProperties = new Properties();
	/**
	 * 邮箱配置文件对象
	 */
	private static Properties mailProperties = new Properties();
	static{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = null;
		try {
			inputStream = classLoader.getResourceAsStream(SYSTEM_FILE);
			systemProperties.load(inputStream);
			inputStream = classLoader.getResourceAsStream(AIL_FILE);
			aliProperties.load(inputStream);
			inputStream = classLoader.getResourceAsStream(MAIL_FILE);
			mailProperties.load(inputStream);
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
	/**
	 * 获取短信配置值
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param name 配置项
	 * @return String
	 */
	public static String getAliConfig(String name) {
		String value = "";
		try {
			value = ConfigProperties.aliProperties.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * 获取邮箱配置值
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param name 配置项
	 * @return String
	 */
	public static String getMailConfig(String name) {
		String value = "";
		try {
			value = ConfigProperties.mailProperties.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
