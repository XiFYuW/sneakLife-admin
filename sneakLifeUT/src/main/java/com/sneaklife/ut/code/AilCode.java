package com.sneaklife.ut.code;

import com.sneaklife.ut.code.properties.ConfigProperties;

public class AilCode {
	/**
	 * Ail短信平台连接超时时间
	 */
	public static final String CONNECTTIMEOUT = ConfigProperties.getAliConfig("ail.defaultConnectTimeout");
	/**
	 * Ail短信平台读取超时时间
	 */
	public static final String READTIMEOUT = ConfigProperties.getAliConfig("ail.defaultReadTimeout");
	/**
	 * 账号id
	 */
	public static final String ACCESSKEYID = ConfigProperties.getAliConfig("ail.accessKeyId");
	/**
	 * 私钥
	 */
	public static final String ACCESSKEYSECRET = ConfigProperties.getAliConfig("ail.accessKeySecret");
	/**
	 * 短信签名
	 */
	public static final String SIGNNAMEDXYZM = ConfigProperties.getAliConfig("ail.signNameDXYZM");
	/**
	 * 短信模板
	 */
	public static final String TEMPLATECODEDXYZM = ConfigProperties.getAliConfig("ail.templateCodeDXYZM");
}
