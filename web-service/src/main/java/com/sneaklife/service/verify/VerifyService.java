package com.sneaklife.service.verify;

import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface VerifyService {

	/**
	 * 验证邮箱
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param map 参数
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> verifyMail(Map<String, Object> map);
	/**
	 * 发送邮件
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param map 参数
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> sendMail(Map<String, Object> map);

	/**
	 * 获取图片验证码二进制流
	 * @author yuanwei
	 * @date 2018年10月13日
	 * @version 1.0
	 * @return BufferedImage
	 */
	public BufferedImage getVerifyImagesStream();
	/**
	 * 获取图片验证码Base64
	 * @author yuanwei
	 * @date 2018年10月13日
	 * @version 1.0
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> getVerifyImagesBase64();
	/**
	 * 获取图片验证码url
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> getVerifyImagesUrl();
	/**
	 * 获取手机验证码
	 * @author yuanwei
	 * @date 2018年10月29日
	 * @version 1.0
	 * @param map 参数
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> getSMS(Map<String, Object> map);
}
