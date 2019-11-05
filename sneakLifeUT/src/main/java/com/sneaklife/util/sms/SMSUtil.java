package com.sneaklife.util.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sneaklife.util.code.AilCode;

public class SMSUtil {

	/**
	 * 发送短信
	 *
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param no
	 *            手机号
	 * @param yam
	 *            验证码
	 * @return =true 成功 =false 失败
	 * @throws ClientException
	 */
	public static boolean sendSMS(String no, String yam) throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", AilCode.CONNECTTIMEOUT);
		System.setProperty("sun.net.client.defaultReadTimeout", AilCode.READTIMEOUT);
		final String product = "Dysmsapi";
		final String domain = "dysmsapi.aliyuncs.com";
		final String accessKeyId = AilCode.ACCESSKEYID;
		final String accessKeySecret = AilCode.ACCESSKEYSECRET;
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(no);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("教学设备管理系统");
		// 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
		request.setTemplateCode(AilCode.TEMPLATECODEDXYZM);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"code\":\"" + yam + "\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			return true;
		}
		return false;
	}
}
