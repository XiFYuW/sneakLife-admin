package com.sneaklife.ut.keyless;

import com.sneaklife.ut.code.constants.Constants;
import com.sneaklife.ut.common.CommonUtil;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

	public static void main(String[] args) throws Exception {
		Map<String, Object> keyPair = initKey();
		String privateKey = getPrivateKey(keyPair);
		String publicKey = getPublicKey(keyPair);
		System.out.println("privateKey: " + privateKey);
		System.out.println("publicKey:" + publicKey);
		System.err.println("公钥加密——私钥解密");
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		System.out.println("加密前文字：" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = encryptByPublicKey(data, publicKey);
		System.out.println("加密后文字：" + Base64Util.base64Encode(encodedData));
		byte[] decodedData = decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: " + target);

		// System.err.println("私钥加密——公钥解密");
		// byte[] encodedDa = encryptByPrivateKey(data, privateKey);
		// System.out.println("加密后：\r\n" + new String(encodedDa));
		// byte[] decodedDa = decryptByPublicKey(encodedDa, publicKey);
		// String tar = new String(decodedDa);
		// System.out.println("解密后: \r\n" + tar);

		// System.err.println("私钥签名——公钥验证签名");
		// String sign = sign(encodedData, privateKey);
		// System.err.println("签名:\r" + sign);
		// boolean status = verify(encodedData, publicKey, sign);
		// System.err.println("验证结果:\r" + status);
	}

	/**
	 * 用私钥对信息生成数字签名
	 *
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		String sing = "";
		// 解密由base64编码的私钥
		byte[] keyBytes = Base64Util.base64Decode(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		sing = Base64Util.base64Encode(signature.sign());
		CommonUtil.setSessionValue(Constants.SIGNA_NAME, sing);
		return sing;
	}

	/**
	 * 校验数字签名
	 *
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密由base64编码的公钥
		byte[] keyBytes = Base64Util.base64Decode(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(Base64Util.base64Decode(sign));
	}

	/**
	 * 用私钥解密
	 *
	 * @param data
	 * @param key
	 *            (BASE64)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
		// 取得私钥
		byte[] keyBytes = Base64Util.base64Decode(key);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > Constants.MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, Constants.MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * Constants.MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 用公钥解密
	 *
	 * @param data
	 * @param key
	 *            (BASE64)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		// 取得公钥
		byte[] keyBytes = Base64Util.base64Decode(key);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > Constants.MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, Constants.MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * Constants.MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 用公钥加密
	 *
	 * @param data
	 * @param key
	 *            (BASE64)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = Base64Util.base64Decode(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > Constants.MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, Constants.MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * Constants.MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 用私钥加密
	 *
	 * @param data
	 * @param key
	 *            (BASE64)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = Base64Util.base64Decode(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > Constants.MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, Constants.MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * Constants.MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 取得私钥
	 *
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		RSAPrivateKey key = (RSAPrivateKey) keyMap.get(Constants.PRIVATE_KEY);
		String pk = Base64Util.base64Encode(key.getEncoded());
		CommonUtil.setSessionValue(Constants.PRIVATE_KEY, pk);
		return pk;
	}

	/**
	 * 取得公钥
	 *
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		RSAPublicKey key = (RSAPublicKey) keyMap.get(Constants.PUBLIC_KEY);
		String bk = Base64Util.base64Encode(key.getEncoded());
		CommonUtil.setSessionValue(Constants.PUBLIC_KEY, bk);
		return bk;
	}

	/**
	 * 初始化密钥对
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Constants.KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<>(2);
		String prk = Base64Util.base64Encode(privateKey.getEncoded());
		String puk = Base64Util.base64Encode(publicKey.getEncoded());
		keyMap.put(Constants.PUBLIC_KEY, puk);
		keyMap.put(Constants.PRIVATE_KEY, prk);
		return keyMap;
	}

}
