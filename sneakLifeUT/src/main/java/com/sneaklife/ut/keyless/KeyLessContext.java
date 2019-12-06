package com.sneaklife.ut.keyless;

import com.sneaklife.pkv.CommonPKV;
import com.sneaklife.ut.date.DateUtil;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 16:51
 */
public class KeyLessContext {

    private static final Logger log = LoggerFactory.getLogger(KeyLessContext.class);

    private static final CommonPKV commonPKV = SpringContextUtil.getBean(CommonPKV.class);

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getKey(String localKey, HashOperations hashOperations) throws SneakLifeException {
        if(hashOperations.hasKey(localKey, commonPKV.getTokenKey())){
            Map<String, Object> rsaKey = (Map<String, Object>) hashOperations.get(localKey, commonPKV.getTokenKey());
            rsaKey.put("ptk", Base64Util.base64Encode(String.valueOf(Objects.requireNonNull(rsaKey).get("ptk")).getBytes()));
            rsaKey.put("link",Base64Util.base64Encode(commonPKV.getServerUrl().getBytes()));
            return rsaKey;
        }
        throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_GQTOKEN.toValue(), RespCode.MSG_GQTOKEN.toMsg(),
                setKey(localKey ,hashOperations)));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> setKey(String localKey, HashOperations hashOperations) throws SneakLifeException {
        Map<String, Object> rsaKey;
        String token = UUID.randomUUID().toString().substring(0, 16);
        try {
            rsaKey = RSAUtil.initRsaKey();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_HQMYDSB.toValue(),RespCode.MSG_HQMYDSB.toMsg()));
        }
        rsaKey.put("ptk", token);
        rsaKey.put("time", DateUtil.getSecond() + commonPKV.getTokenCacheTimes());
        hashOperations.put(localKey, commonPKV.getTokenKey(), rsaKey);
        rsaKey.remove("prk");
        rsaKey.put("ptk", Base64Util.base64Encode(token.getBytes()));
        rsaKey.put("link",Base64Util.base64Encode(commonPKV.getServerUrl().getBytes()));
        return rsaKey;
    }

    public static String getRsaData(String localKey, String rsaData, HashOperations hashOperations) throws Exception {
        Map<String, Object> kMap = getKey(localKey, hashOperations);
        String prk = String.valueOf(kMap.get("prk"));
        log.info("私钥：{}", prk);
        String puk = String.valueOf(kMap.get("puk"));
        log.info("公钥：{}", puk);
        byte[] ba = Base64Util.base64Decode(rsaData);
        String sign = RSAUtil.sign(ba, prk);
            boolean boo = RSAUtil.verify(ba, puk, sign);
            if (!boo) {
            return "";
        }
        byte[] data;
        try {
            data = RSAUtil.decryptByPrivateKey(ba, prk);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_RSA_JMSB.toValue(),RespCode.MSG_RSA_JMSB.toMsg()));
        }
        return new String(data);
    }

    @SuppressWarnings("unchecked")
    public static boolean checkToken(String localKey, String token, HashOperations hashOperations) throws Exception{
        Map<String, Object> map = getKey(localKey, hashOperations);
        String ptk = String.valueOf(map.get("ptk"));
        Long time = (Long) map.get("time");
        if(ptk.equals(token) || DateUtil.getSecond() > time){
            hashOperations.delete(localKey, commonPKV.getTokenKey());
            return false;
        }
        return true;
    }
}
