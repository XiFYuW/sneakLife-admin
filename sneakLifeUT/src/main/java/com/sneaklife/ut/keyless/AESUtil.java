package com.sneaklife.ut.keyless;


import com.sneaklife.ut.code.constants.Constants;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Map;

public class AESUtil {

//	/**
//	 * aes解密
//	 *
//	 * @param encrypt
//	 *            内容
//	 * @return
//	 * @throws Exception
//	 */
//	public static String aesDecrypt(String encrypt) throws Exception {
//		Map<String, Object> map = CommonUtil.getTokenMap();
//		return aesDecrypt(encrypt, String.valueOf(map.get("ptk")));
//	}
//
//	/**
//	 * aes加密
//	 *
//	 * @param content
//	 * @return
//	 * @throws Exception
//	 */
//	public static String aesEncrypt(String content) throws Exception {
//		Map<String, Object> map = CommonUtil.getTokenMap();
//		return aesEncrypt(content, String.valueOf(map.get("hyesptk")));
//	}

	/**
	 * AES加密
	 *
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		Cipher cipher = Cipher.getInstance(Constants.ALGORITHMSTR);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * AES加密为base 64 code
	 *
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return Base64Util.base64Encode(aesEncryptToBytes(content, encryptKey));
	}

	/**
	 * AES解密
	 *
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		Cipher cipher = Cipher.getInstance(Constants.ALGORITHMSTR);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		return new String(decryptBytes, "utf-8");
	}

	/**
	 * 将base 64 code AES解密
	 *
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
		return StringUtils.isEmpty(encryptStr) ? null
				: aesDecryptByBytes(Base64Util.base64Decode(encryptStr), decryptKey);

	}

	/**
	 * 将byte[]转为各种进制的字符串
	 *
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);
	}
}
