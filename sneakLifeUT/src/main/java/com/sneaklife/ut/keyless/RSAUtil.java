package com.sneaklife.ut.keyless;

import com.sneaklife.pkv.RsaPKV;
import com.sneaklife.ut.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//		Map<String, Object> keyPair = initRsaKey();
//		RSAPrivateKey prKey = (RSAPrivateKey) keyPair.get(rsaPKV.getPrivateKey());
//		String privateKey = Base64Util.base64Encode(prKey.getEncoded());
//		RSAPublicKey puKey = (RSAPublicKey) keyPair.get(rsaPKV.getPublicKey());
//		String publicKey = Base64Util.base64Encode(puKey.getEncoded());
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKhnFOxdVHN2X8zFni2YfKwHlhrx+luV2JErdF0AM+z5vkeK4GQqMpl46wqcYalOHTfCZKGf6jKbRBEYbMq1iI/kZ+xAtznzmPY3INE4/GpV/iFRADn/t7B3ZsJIO0WTxxbIEJQ2P2Ty6BPvj79uLkLSG3G3YuqMO9C3Vdq6hAthAgMBAAECgYBuXRADT6729I8YHjLp4tNh0p73DbprpyqFCRt7j5R4K7weGs+wFU+P8QTosU/3qu/PCSD2I11SyRq0sXMq7gmOYxQMI1XT4E/INwyXbZW1ly04rPViw8IQTi1lTxuRPjWbqDc1PHJ9qL1A7he3KzggdpaxSUbLTENfrqPxSbuYeQJBANyx6Cj7yYk4J7+ZMUUIIksECOhOf+tTcefB5x1dS0ZfHloW6ggOLRQ3DLOib+cO9HMmMPioTi1X1bla4PLM6wMCQQDDV6fB8ChjldbUlDv1vE5bC1uLyfCTLTq1kOE+e8uuabAVRGNKDYndsldt8WdmUgHh7/LqAEjh+uF1JK/onJDLAkAsrSs1WLtUtvR8+fMaaWofy4JOHM4SgoL1irBRpng9l7XaQPdBiDUh+X7twn1qxgFAakPRoqiozzYp3rbZEBinAkA3f+QN2enfwWa0JxmSC9pHS8bKxKkxZ5xSkcMtOYb5GtYIdNGw7H/LGSx19Cxoicw3ITFnAoWyjtEXs0C3WiRZAkAejTaEfNokVqN2TpbIhXGgYOI3YdGXC+wuhwQ4mppoksJjmQ0ARKrYPjhZ2a1Hj9bMpJ0D2B84byqBh+JpkHnL";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoZxTsXVRzdl/MxZ4tmHysB5Ya8fpbldiRK3RdADPs+b5HiuBkKjKZeOsKnGGpTh03wmShn+oym0QRGGzKtYiP5GfsQLc585j2NyDROPxqVf4hUQA5/7ewd2bCSDtFk8cWyBCUNj9k8ugT74+/bi5C0htxt2LqjDvQt1XauoQLYQIDAQAB";
        System.out.println("privateKey: " + privateKey);
        System.out.println("publicKey:" + publicKey);
        System.err.println("公钥加密——私钥解密");
        String source = "{\"data\":\"vgDT0K+6yb4lkkFhfwTjeYYvR3YvhPkmAo4krm81+Qakql7zAOJJwUBFqJ4hHOgImpdaUeMQGkhLg0zpWo7y8UyfqWwwTdRujwNAH0wFqCFuYxQbeNhJisCXjso+m7P2Upl2KXzOjjJXJ2BYsevQPpGz/wkcjjZSn3em6TcykXguKXCylwuUsUqMPd06BBnEzOQPip0cYUN7VgAewURYlA==\",\"token\":\"VDBNlLK1syp8WIQRnZuwy/6n2Huzsiss4CSjYkq/XlkhXYshddmiIYyzYkODBL8av9wGR3JlkowjN3/u2vn0MzQqypcorPQpFyMsOa3h3NWNGEt0UfLJ2DnF8XAwIRpvRPrfUv26BdkrNhKiquRMAHxHZ0QFSY7zHfte6V9CsfQ=\"}";
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

    private static final Logger log = LoggerFactory.getLogger(RSAUtil.class);

    private static Cipher getCipherObject() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(rsaPKV.getCipherAlgorithm());
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
     * @param data       加密数据
     * @param privateKey 私钥
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
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
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

    private static byte[] decryptAndEncrypt(Cipher cipher, byte[] data, int maxDE) throws IllegalBlockSizeException, IOException, BadPaddingException {
        int len = data.length;
        log.info("解密数据长度：{}", len);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        int ms = len % maxDE;
        // 缺少了几位
        int letTemp = 0;
        if (ms != 0) {
            letTemp = maxDE - ms;
        }
        if(letTemp > 1){
            System.out.println(1);
        }
        // 对数据分段解密  目前只能确定data缺少了几位,还无法确定data分段缺少了几位
        while (len - offSet > 0) {
            if (len - offSet > maxDE) {
                try {
                    cache = cipher.doFinal(data, offSet, maxDE);
                } catch (BadPaddingException e) {
                    byte[] dataTemp = new byte[len + letTemp];
                    for (int n = 0; n < letTemp; n++) {
                        dataTemp[offSet + n] = 0x00;
                    }
                    for (int j = offSet; j < len; j++) {
                        dataTemp[j + letTemp] = data[j];
                    }
                    for (int z = 0; z < offSet; z++) {
                        dataTemp[z] = data[z];
                    }
                    data = dataTemp;
                    len = dataTemp.length;
                    cache = cipher.doFinal(data, offSet, maxDE);
                }
            } else {
                try {
                    cache = cipher.doFinal(data, offSet, len - offSet);
                } catch (BadPaddingException e) {
                    byte[] dataTemp = new byte[len + letTemp];
                    for (int n = 0; n < letTemp; n++) {
                        dataTemp[n] = 0x00;
                    }
                    for (int j = letTemp; j <= len; j++) {
                        dataTemp[j] = data[j - 1];
                    }
                    data = dataTemp;
                    len = dataTemp.length;
                    cache = cipher.doFinal(data, offSet, len - offSet);
                }
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxDE;
        }
        byte[] de = out.toByteArray();
        out.close();
        return de;
    }

    /**
     * 用私钥解密
     *
     * @param data
     * @param key  (BASE64)
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
     * @param key  (BASE64)
     * @return byte[]
     * @throws Exception s
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
     * @param key  (BASE64)
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
     * @param key  (BASE64)
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
