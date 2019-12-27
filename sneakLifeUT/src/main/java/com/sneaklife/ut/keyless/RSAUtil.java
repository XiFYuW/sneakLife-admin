package com.sneaklife.ut.keyless;

import com.sneaklife.config.pkv.RsaPKV;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
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

    /**
     * 获取Cipher对象
     * @return Cipher
     * @throws NoSuchAlgorithmException 异常信息
     * @throws NoSuchPaddingException 异常信息
     */
    private static Cipher getCipherObject() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(rsaPKV.getCipherAlgorithm());
    }

    /**
     * 获取KeyFactory对象
     * @return KeyFactory
     * @throws NoSuchAlgorithmException 异常信息
     */
    private static KeyFactory getKeyFactoryObject() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(rsaPKV.getKeyAlgorithm());
    }

    /**
     * 获取PrivateKey对象
     * @param privateKey 私钥
     * @return PrivateKey
     * @throws Exception 异常信息
     */
    private static PrivateKey getPrivateKeyObject(String privateKey) throws Exception {
        byte[] keyBytes = Objects.requireNonNull(Base64Util.base64Decode(privateKey));
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = getKeyFactoryObject();
        return keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 获取PublicKey对象
     * @param publicKey 公钥
     * @return PublicKey
     * @throws Exception 异常信息
     */
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
     * @param data 加密数据
     * @param privateKey 私钥
     * @return String
     * @throws Exception 异常信息
     */
    static String sign(byte[] data, String privateKey) throws Exception {
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
     * @throws Exception 异常信息
     */
    static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        PublicKey pubKey = getPublicKeyObject(publicKey);
        Signature signature = getSignatureObject();
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(Base64Util.base64Decode(sign));
    }

    /**
     * 用私钥解密
     * @param data 加密字节数组
     * @param key 私钥
     * @return byte[]
     * @throws Exception 异常信息
     */
    static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        PrivateKey privateKey = getPrivateKeyObject(key);
        Cipher cipher = getCipherObject();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return decryptAndEncrypt(cipher, data, rsaPKV.getMaxDecryptBlock());
    }

    /**
     * 用公钥解密
     * @param data 加密字节数组
     * @param key 公钥
     * @return byte[]
     * @throws Exception 异常信息
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
     * @param data 原文字节数组
     * @param key 公钥
     * @return byte[]
     * @throws Exception 异常信息
     */
    private static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        RSAPublicKey publicKey = (RSAPublicKey) getPublicKeyObject(key);
        Cipher cipher = getCipherObject();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return decryptAndEncrypt(cipher, data, rsaPKV.getMaxEncryptBlock());
    }

    /**
     * 用私钥加密
     * @param data 原文字节数组
     * @param key 私钥
     * @return byte[]
     * @throws Exception 异常信息
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        PrivateKey privateKey = getPrivateKeyObject(key);
        Cipher cipher = getCipherObject();
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return decryptAndEncrypt(cipher, data, rsaPKV.getMaxEncryptBlock());
    }

    /**
     * 初始化密钥对
     * @return Map<String, Object>
     * @throws Exception 异常信息
     */
    static Map<String, Object> initRsaKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(rsaPKV.getKeyAlgorithm());
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);
        String prk = Base64Util.base64Encode(privateKey.getEncoded());
        if(!IwsContext.isNotNull(prk)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_RSA_PRK.toValue(),RespCode.MSG_RSA_PRK.toMsg()));
        }
        String puk = Base64Util.base64Encode(publicKey.getEncoded());
        if(!IwsContext.isNotNull(puk)){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_RSA_PUK.toValue(),RespCode.MSG_RSA_PUK.toMsg()));
        }
        keyMap.put(rsaPKV.getPublicKey(), puk);
        keyMap.put(rsaPKV.getPrivateKey(), prk);
        return keyMap;
    }

    private static byte[] decryptAndEncrypt(Cipher cipher, byte[] data, int maxDE) throws IllegalBlockSizeException, IOException, BadPaddingException {
        int len = data.length;
        log.info("解密数据长度：{}", len);
        int offSet = 0;
        byte[] cache;
        int i = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 缺少了几位
        int letTemp = getLetTemp(len, maxDE);
        if(letTemp > 1){
            System.out.println(1);
        }
        while (len - offSet > 0) {
            if (len - offSet > maxDE) {
                try {
                    cache = cipher.doFinal(data, offSet, maxDE);
                } catch (BadPaddingException e) {
                    Map<String,Object> map = doSupplement(cipher, len, letTemp, offSet, maxDE, data);
                    cache = (byte[]) map.get("c");
                    data = (byte[]) map.get("data");
                    len = (int) map.get("len");
                }
            } else {
                try {
                    cache = cipher.doFinal(data, offSet, len - offSet);
                } catch (BadPaddingException e) {
                    Map<String,Object> map = doLeastSupplement(cipher, len, letTemp, offSet, data);
                    cache = (byte[]) map.get("c");
                    data = (byte[]) map.get("data");
                    len = (int) map.get("len");
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
     * 获取需要补位数
     * @param len 密文字节数组总长度
     * @param maxDE 每次最大长度：128
     * @return int
     */
    private static int getLetTemp(int len, int maxDE){
        int ms = len % maxDE;
        int letTemp = 0;
        if (ms != 0) {
            letTemp = maxDE - ms;
        }
        return letTemp;
    }

    /**
     * 分段补位解密
     * @param cipher Cipher对象
     * @param offSet 分段开始位置
     * @param end 分段结束位置
     * @param data 密文字节数组
     * @return byte[]
     * @throws IllegalBlockSizeException 异常信息
     * @throws BadPaddingException 异常信息
     */
    private static byte[] doFinal(Cipher cipher, int offSet, int end, byte[] data) throws IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal(data, offSet, end);
    }

    /**
     * 分段补位方法
     * @param cipher Cipher对象
     * @param len 密文字节数组总长度
     * @param letTemp 补位数
     * @param offSet 分段开始位置
     * @param maxDE 每次最大长度：128
     * @param data 密文字节数组
     * @return Map<String,Object>
     * @throws IllegalBlockSizeException 异常信息
     * @throws BadPaddingException 异常信息
     */
    private static Map<String,Object> doSupplement(Cipher cipher, int len, int letTemp, int offSet, int maxDE, byte[] data) throws IllegalBlockSizeException, BadPaddingException {
        byte[] c;
        data = supplement(len, letTemp, offSet, data);
        len = data.length;
        try {
            c = doFinal(cipher, offSet, maxDE, data);
        } catch (BadPaddingException e) {
            int k = letTemp - 1;
            if (k <= 0) {
                throw e;
            }
            Map<String,Object> map = doSupplement(cipher, len, k, offSet, maxDE, data);
            c = (byte[]) map.get("c");
        }
        return toMap(c, data, len);
    }

    /**
     *  分段补位方法，不包含最后一次
     * @param len 密文字节数组总长度
     * @param letTemp 补位数
     * @param offSet 分段起始位置
     * @param data 密文字节数组
     * @return byte[]
     */
    private static byte[] supplement(int len, int letTemp, int offSet, byte[] data) {
        byte[] dataTemp = new byte[len + letTemp];
        for (int n = 0; n < letTemp; n++) {
            dataTemp[offSet + n] = 0x00;
        }
        if (len - offSet >= 0) {
            System.arraycopy(data, offSet, dataTemp, offSet + letTemp, len - offSet);
        }
        if (offSet >= 0) {
            System.arraycopy(data, 0, dataTemp, 0, offSet);
        }
        return dataTemp;
    }

    /**
     * 最后总长度少于128时的补位方法
     * @param cipher Cipher对象
     * @param len 密文字节数组总长度
     * @param letTemp 补位数
     * @param offSet 分段起始位置
     * @param data 密文字节数组
     * @return Map<String,Object>
     * @throws IllegalBlockSizeException 异常信息
     * @throws BadPaddingException 异常信息
     */
    private static Map<String,Object> doLeastSupplement(Cipher cipher, int len, int letTemp, int offSet, byte[] data) throws IllegalBlockSizeException, BadPaddingException {
        byte[] c;
        data = supplement(len, letTemp, offSet, data);
        len = data.length;
        try {
            c = doFinal(cipher, offSet, len - offSet, data);
        } catch (BadPaddingException e) {
            int k = letTemp - 1;
            if(k <= 0){
                throw e;
            }
            Map<String,Object> map = doLeastSupplement(cipher, len, k, offSet, data);
            c = (byte[]) map.get("c");
        }
        return toMap(c, data, len);
    }

    /**
     * 单独总长度少于128时的补位方法
     * @param len 密文字节数组总长度
     * @param letTemp 补位数
     * @param data 密文字节数组
     * @return byte[]
     */
    private static byte[] leastSupplement(int len, int letTemp, byte[] data){
        byte[] dataTemp = new byte[len + letTemp];
        for (int n = 0; n < letTemp; n++) {
            dataTemp[n] = 0x00;
        }
        if (len + 1 - letTemp >= 0){
            System.arraycopy(data, letTemp - 1, dataTemp, letTemp, len + 1 - letTemp);
        }
        return dataTemp;
    }

    /**
     * 以map的形式返回数据
     * @param c 解密之后的字节数组
     * @param data 密文字节数组
     * @param len 密文字节数组总长度
     * @return Map<String,Object>
     */
    private static Map<String,Object> toMap(byte[] c, byte[] data, int len){
        Map<String,Object> map = new HashMap<>(3);
        map.put("c", c);
        map.put("data", data);
        map.put("len", len);
        return map;
    }
}
