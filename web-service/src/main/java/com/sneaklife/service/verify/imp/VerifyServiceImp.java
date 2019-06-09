package com.sneaklife.service.verify.imp;

import com.aliyuncs.exceptions.ClientException;
import com.sneaklife.code.FileCode;
import com.sneaklife.common.CommonUtil;
import com.sneaklife.constants.Constants;
import com.sneaklife.date.DateUtil;
import com.sneaklife.images.ImageUtil;
import com.sneaklife.mail.MailUtil;
import com.sneaklife.redis.RedisLoader;
import com.sneaklife.redis.RedisUtil;
import com.sneaklife.resp.RespCode;
import com.sneaklife.service.verify.VerifyService;
import com.sneaklife.sms.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyServiceImp implements VerifyService {
	private final static RedisUtil REDISUTIL = RedisLoader.load();
	private TaskExecutor taskExecutor;
	@Override
	public ResponseEntity<String> verifyMail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String codes = CommonUtil.getMapTo(map, "code", String.class);
		if(codes == null || codes == "") {
			return CommonUtil.respResult(RespCode.MSG_YZMAILBNWK.toValue(), RespCode.MSG_YZMAILBNWK.toMsg());
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> yz = (Map<String, Object>) REDISUTIL.hashGet(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.MAILCODE);
		Long times = (Long) yz.get("tiem");
		String code = String.valueOf(yz.get("code"));
		if (DateUtil.getSecond() >= times) {
			return CommonUtil.respResult(RespCode.MSG_YZMAILGQ.toValue(), RespCode.MSG_YZMAILGQ.toMsg());
		}
		if(!code.equals(codes)) {
			return CommonUtil.respResult(RespCode.MSG_YZMAILSB.toValue(), RespCode.MSG_YZMAILSB.toMsg());
		}
		return CommonUtil.respResult(RespCode.MSG_YZMAILCG.toValue(), RespCode.MSG_YZMAILCG.toMsg());
	}

	@Override
	public ResponseEntity<String> sendMail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String mail = CommonUtil.getMapTo(map, "mails", String.class);
		if (!CommonUtil.isNull(mail)) {
			return CommonUtil.respResult(RespCode.MSG_YXDZCW.toValue(), RespCode.MSG_YXDZCW.toMsg());
		}
		String code = CommonUtil.getRandom();
		this.taskExecutor.execute(new MailUtil(mail, code));
		//new Thread(new MailUtil(mail, code)).start();
		map.clear();
		map.put("tiem", DateUtil.getSecond() + Constants.MAIL_TIMES);
		map.put("code", code);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.MAILCODE, map);
		return CommonUtil.respResult(RespCode.MSG_SENDMAILCG.toValue(), RespCode.MSG_SENDMAILCG.toMsg());
	}

	@Override
	public ResponseEntity<String> getSMS(Map<String, Object> map) {
		// TODO Auto-generated method stub
		CommonUtil.disposeNull(map);
		String smsNo = CommonUtil.getMapTo(map, "sms_no", String.class);
		String yam = CommonUtil.getRandom();
		try {
			SMSUtil.sendSMS(smsNo, yam);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.clear();
		Long times = DateUtil.getSecond() + Constants.SMS_TIMES;
		map.put("sms", yam);
		map.put("time", times);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.SJYZM, map);
		return CommonUtil.respResult(RespCode.MSG_SUCCEED.toValue(), map);
	}

	@Override
	public ResponseEntity<String> getVerifyImagesUrl() {
		// TODO Auto-generated method stub
		String ver = ImageUtil.getSecurityCode();
		String path = ImageUtil.buildServerPath(ImageUtil.getImageAsInputStream(ver), ver, FileCode.FILE_PNG.toValue());
		Map<String, Object> map = new HashMap<>();
		Long times = DateUtil.getSecond() + Constants.IMAGE_TIMES;
		map.put("verify", ver);
		map.put("time", times);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.COOKVERIFY, map);
		map.put("verify", path);
		CommonUtil.setResponseNoCache();
		return CommonUtil.respResult(RespCode.MSG_SUCCEED.toValue(), map);
	}

	@Override
	public BufferedImage getVerifyImagesStream() {
		// TODO Auto-generated method stub
		String ver = ImageUtil.getSecurityCode();
		BufferedImage bufferedImage = ImageUtil.createImage(ver);
		Map<String, Object> map = new HashMap<>();
		Long times = DateUtil.getSecond() + Constants.IMAGE_TIMES;
		map.put("verify", ver);
		map.put("time", times);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.COOKVERIFY, map);
		CommonUtil.setResponseNoCache();
		return bufferedImage;
	}

	@Override
	public ResponseEntity<String> getVerifyImagesBase64() {
		// TODO Auto-generated method stub
		String ver = ImageUtil.getSecurityCode();
		BufferedImage bufferedImage = ImageUtil.createImage(ver);
		String base64 = ImageUtil.getImageBase64(bufferedImage);
		Map<String, Object> map = new HashMap<>();
		Long times = DateUtil.getSecond() + Constants.IMAGE_TIMES;
		map.put("verify", ver);
		map.put("time", times);
		REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), CommonUtil.getSessionId(), Constants.COOKVERIFY, map);
		map.put("verify", "data:image/jpg;base64," + base64);
		CommonUtil.setResponseNoCache();
		return CommonUtil.respResult(2000, map);
	}
}
