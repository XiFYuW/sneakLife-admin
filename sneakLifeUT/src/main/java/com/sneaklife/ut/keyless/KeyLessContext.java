package com.sneaklife.ut.keyless;

import com.sneaklife.ut.code.constants.Constants;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;

import java.util.Map;
import java.util.UUID;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 16:51
 */
public class KeyLessContext {

    private static final Logger log = LoggerFactory.getLogger(KeyLessContext.class);

    private static final String TOKEN_KEY = "TOKEN_KEY";

    @SuppressWarnings("unchecked")
    public static Map<String, Object> setKey(String localKey, HashOperations hashOperations) throws SneakLifeException {
        Map<String, Object> rsaKey;
        if(hashOperations.hasKey(localKey, TOKEN_KEY)){
            rsaKey = (Map<String, Object>) hashOperations.get(localKey, TOKEN_KEY);
            rsaKey.put("ptk", Base64Util.base64Encode(String.valueOf(rsaKey.get("ptk")).getBytes()));
            rsaKey.put("link",Base64Util.base64Encode(Constants.SERVICE_URL.getBytes()));
            return rsaKey;
        }
        String token = UUID.randomUUID().toString().substring(0, 16);
        try {
            rsaKey = RSAUtil.initRsaKey();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SneakLifeException(IwsContext.respResultBody(1,"12"));
        }
        rsaKey.put("ptk", token);
        rsaKey.put("time", DateUtil.getSecond() + Constants.TOKEN_CACHE_TIMES);
        hashOperations.put(localKey, TOKEN_KEY, rsaKey);
        rsaKey.remove("prk");
        rsaKey.put("ptk", Base64Util.base64Encode(token.getBytes()));
        rsaKey.put("link",Base64Util.base64Encode(Constants.SERVICE_URL.getBytes()));
        return rsaKey;
    }

    public static String getRsaData(String localKey, String rsaData, HashOperations hashOperations) throws Exception {
        Map<String, Object> kMap = setKey(localKey, hashOperations);
        String prk = String.valueOf(kMap.get("prk"));
        String puk = String.valueOf(kMap.get("puk"));
        byte[] ba = Base64Util.base64Decode(rsaData);
        String sign = RSAUtil.sign(ba, prk);
        boolean boo = RSAUtil.verify(ba, puk, sign);
        if (!boo) {
            return "";
        }
        byte[] data = RSAUtil.decryptByPrivateKey(ba, prk);
        return new String(data);
    }

    @SuppressWarnings("unchecked")
    public static boolean checkToken(String localKey, String token, HashOperations hashOperations) throws Exception{
        Map<String, Object> map = setKey(localKey, hashOperations);
        String ptk = String.valueOf(map.get("ptk"));
        Long time = (Long) map.get("time");
        if(ptk.equals(token) || DateUtil.getSecond() > time){
            hashOperations.delete(localKey,TOKEN_KEY);
            return false;
        }
        return true;
    }
}
