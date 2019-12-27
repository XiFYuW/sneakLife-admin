package com.sneaklife.ut.keyless;


import com.sneaklife.config.pkv.AesPKV;
import com.sneaklife.ut.spring.SpringContextUtil;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

public class AESUtil {

	private static final AesPKV aesPKV = SpringContextUtil.getBean(AesPKV.class);

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
		Cipher cipher = Cipher.getInstance(aesPKV.getKeyAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), aesPKV.getSignatureName()));
		return cipher.doFinal(content.getBytes(aesPKV.getCharset()));
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
		Cipher cipher = Cipher.getInstance(aesPKV.getKeyAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), aesPKV.getSignatureName()));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		return new String(decryptBytes, aesPKV.getCharset());
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
