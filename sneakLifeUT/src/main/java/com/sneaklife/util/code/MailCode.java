package com.sneaklife.util.code;

import com.sneaklife.util.code.properties.ConfigProperties;

public class MailCode {

	/**
	 * 发送人邮箱
	 */
	public static final String SENDMAIL = ConfigProperties.getMailConfig("sendMail");
	/**
	 * 端口
	 */
	public static final String MHOST = ConfigProperties.getMailConfig("mhost");
	/**
	 * 签名
	 */
	public static final String SQM = ConfigProperties.getMailConfig("sqm");
	/**
	 * 认证
	 */
	public static final String AUTH =  ConfigProperties.getMailConfig("auth");
}
