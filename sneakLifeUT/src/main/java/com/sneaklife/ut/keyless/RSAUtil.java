package com.sneaklife.ut.keyless;

import com.sneaklife.pkv.RsaPKV;
import com.sneaklife.ut.spring.SpringContextUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RSAUtil {

	public static void main(String[] args) throws Exception {
		Map<String, Object> keyPair = initRsaKey();
		RSAPrivateKey prKey = (RSAPrivateKey) keyPair.get(rsaPKV.getPrivateKey());
		String privateKey = Base64Util.base64Encode(prKey.getEncoded());
		RSAPublicKey puKey = (RSAPublicKey) keyPair.get(rsaPKV.getPublicKey());
		String publicKey = Base64Util.base64Encode(puKey.getEncoded());
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

	private static final RsaPKV rsaPKV = SpringContextUtil.getBean(RsaPKV.class);

	private static Cipher getCipherObject() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance(getKeyFactoryObject().getAlgorithm());
	}

	private static KeyFactory getKeyFactoryObject() throws NoSuchAlgorithmException {
		return KeyFactory.getInstance(rsaPKV.getKeyAlgorithm());
	}

	private static PrivateKey getPrivateKeyObject(String privateKey) throws Exception {
		byte[] keyBytes = Objects.requireNonNull(Base64Util.base64Decode(privateKey));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = getKeyFactoryObject();
		return keyFactory.generatePrivate(pkcs8KeySpec);
	}

	private static PublicKey getPublicKeyObject(String publicKey) throws Exception {
		byte[] keyBytes = Objects.requireNonNull(Base64Util.base64Decode(publicKey));
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = getKeyFactoryObject();
		return keyFactory.generatePublic(keySpec);
	}

	private static Signature getSignatureObject() throws NoSuchAlgorithmException {
		return Signature.getInstance(rsaPKV.getSignatureAlgorithm());
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
		PrivateKey priKey = getPrivateKeyObject(privateKey);
		Signature signature = getSignatureObject();
		signature.initSign(priKey);
		signature.update(data);
		return Base64Util.base64Encode(signature.sign());
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
		PublicKey pubKey = getPublicKeyObject(publicKey);
		Signature signature = getSignatureObject();
		signature.initVerify(pubKey);
		signature.update(data);
		return signature.verify(Base64Util.base64Decode(sign));
	}

	private static byte[] decryptAndEncrypt(Cipher cipher, byte[] data, int MaxDE) throws BadPaddingException, IllegalBlockSizeException, IOException {
		int inputLen = data.length;
		System.out.println(inputLen);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MaxDE) {
				cache = cipher.doFinal(data, offSet, MaxDE);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MaxDE;
		}
		byte[] de = out.toByteArray();
		out.close();
		return de;
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
		PrivateKey privateKey = getPrivateKeyObject(key);
		Cipher cipher = getCipherObject();
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return decryptAndEncrypt(cipher, data, rsaPKV.getMaxDecryptBlock());
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
		PublicKey publicKey = getPublicKeyObject(key);
		Cipher cipher = getCipherObject();
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return decryptAndEncrypt(cipher, data, rsaPKV.getMaxDecryptBlock());
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
		RSAPublicKey publicKey = (RSAPublicKey) getPublicKeyObject(key);
		Cipher cipher = getCipherObject();
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return decryptAndEncrypt(cipher, data, rsaPKV.getMaxEncryptBlock());
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
		PrivateKey privateKey = getPrivateKeyObject(key);
		Cipher cipher = getCipherObject();
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return decryptAndEncrypt(cipher, data, rsaPKV.getMaxEncryptBlock());
	}

	/**
	 * 初始化密钥对
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initRsaKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(rsaPKV.getKeyAlgorithm());
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<>(2);
		String prk = Base64Util.base64Encode(privateKey.getEncoded());
		String puk = Base64Util.base64Encode(publicKey.getEncoded());
		keyMap.put(rsaPKV.getPublicKey(), puk);
		keyMap.put(rsaPKV.getPrivateKey(), prk);
		return keyMap;
	}

}
