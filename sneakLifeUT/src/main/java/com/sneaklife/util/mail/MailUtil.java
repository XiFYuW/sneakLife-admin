package com.sneaklife.util.mail;

import com.sneaklife.util.code.MailCode;
import com.sneaklife.util.code.constants.Constants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil implements Runnable {

	/**
	 * 收件人邮箱
	 */
	private String email;
	/**
	 * 激活码
	 */
	private String code;

	public MailUtil(String email, String code) {
		this.email = email;
		this.code = code;
	}

	@Override
	public void run() {
		// 发件人电子邮箱
		String from = MailCode.SENDMAIL;
		// 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
		String host = MailCode.MHOST;
		String sqm = MailCode.SQM;
		// 打开认证
		String auth = MailCode.AUTH;

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", auth);

		try {
			// QQ邮箱需要下面这段代码，163邮箱不需要
			// MailSSLSocketFactory sf = new MailSSLSocketFactory();
			// sf.setTrustAllHosts(true);
			// properties.put("mail.smtp.ssl.enable", "true");
			// properties.put("mail.smtp.ssl.socketFactory", sf);

			// 1.获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					// 发件人邮箱账号、授权码
					return new PasswordAuthentication(from, sqm);
				}
			});

			// 2.创建邮件对象
			Message message = new MimeMessage(session);
			// 2.1设置发件人
			message.setFrom(new InternetAddress(from));
			// 2.2设置接收人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 2.3设置邮件主题
			message.setSubject("湖应教学设备管理系统关联信息邮箱验证");
			// 2.4设置邮件内容
			StringBuffer content = new StringBuffer();
			content.append("<html><head><title>湖应教学设备管理系统邮箱验证</title>");
			content.append("</head><body><h1 style=\"font-size: 30px;\">【湖应教学设备管理系统】</h1>");
			content.append("<h2>尊敬的<span style=\"color: aqua;\">");
			content.append(email);
			content.append("</span>您好！！</h2>");
			content.append("<h3>您本次的关联信息验证码为：<span style=\"color: red\">");
			content.append(code);
			content.append("</span>,请在<span style=\"color: coral;\">");
			content.append(Constants.MAIL_TIMES / 60);
			content.append("</span>分钟内完成验证！</h3></body></html>");
			message.setContent(content.toString(), "text/html;charset=UTF-8");
			// 3.发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
