package com.sneaklife.common;

import com.alibaba.fastjson.JSON;
import com.sneaklife.constants.Constants;
import com.sneaklife.date.DateUtil;
import com.sneaklife.exception.SneakLifeException;
import com.sneaklife.keyless.Base64Util;
import com.sneaklife.keyless.RSAUtil;
import com.sneaklife.page.PageInfo;
import com.sneaklife.redis.RedisLoader;
import com.sneaklife.redis.RedisUtil;
import com.sneaklife.resp.RespCode;
import com.sneaklife.resp.RespResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

public class CommonUtil {

    private final static RedisUtil REDISUTIL = RedisLoader.load();
    private static RespResult respResult;
    private static MultiValueMap<String, String> headers = new HttpHeaders();
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static HttpServletRequest getRequest() {
        return request;
    }

    public static void setRequest(HttpServletRequest request) {
        CommonUtil.request = request;
    }

    public static HttpServletResponse getResponse() {
        return response;
    }

    public static void setResponse(HttpServletResponse response) {
        CommonUtil.response = response;
    }

    public static void setCrossDomain() {
        headers.set("Access-Control-Allow-Origin", request.getHeader("Origin"));
        headers.set("Access-Control-Allow-Credentials", "true");
    }

    public static void setExcelHeaders(String excelName) throws UnsupportedEncodingException {
        setCrossDomain();
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String(excelName.getBytes(), "iso-8859-1"));
    }

    public static OutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public static void closeOutputStream() throws IOException {
        getOutputStream().flush();
        getOutputStream().close();
    }

    public static String getServerPath() {
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + getContextPath() + "/";
        return basePath;
    }

    public static String getContextPath() {
        return request.getContextPath();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> T getSessionValue(String key, Class<T> clas) {
        return (T) request.getSession().getAttribute(key);
    }

    public static String getSessionId() {
        return request.getSession().getId();
    }

    public static void setSessionValue(String key, String value) {
        request.getSession().setAttribute(key, value);
    }

    public static void initHttp(HttpServletRequest req, HttpServletResponse resp) {
        request = req;
        response = resp;
        log.info("sessionId: {}", getSessionId());
    }

    public static void setResponseNoCache() {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
    }

    public static Map<String, Object> sendToken() throws Exception {
        return setKey();
    }

    public static String getRandom() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    public static String getTempUuid() {
        return UUID.randomUUID().toString();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getData(HttpServletRequest request) {
        try {
            String object = String.valueOf(request.getAttribute("data"));
            Map<String, Object> data = (Map<String, Object>) JSON.parse(object);
            log.info("提取前台请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static PageInfo getPageInfo(HttpServletRequest request) {
        try {
            String object = String.valueOf(request.getAttribute("pag"));
            PageInfo data = JSON.parseObject(object,PageInfo.class);
            log.info("提取前台分页请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getListData(HttpServletRequest request) {
        try {
            String object = String.valueOf(request.getAttribute("data"));
            List<Map<String, Object>> data = (List<Map<String, Object>>) JSON.parse(object);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    public static boolean isNull(Object ob) {
        return Optional.ofNullable(ob).isPresent();
    }

    public static String digest(String data, String type) {
        String result = "";
        try {
            MessageDigest sha = MessageDigest.getInstance(type);
            byte[] bytes = sha.digest(data.getBytes("UTF-8"));
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return result;
    }

    public static ResponseEntity<String> respResult(int code, Object data) {
        respResult = new RespResult();
        respResult.setRespCode(code);
        respResult.setRespData(data);
        log.info("返回数据为：【{}】",respResult);
        setCrossDomain();
        return new ResponseEntity<>(JSON.toJSONString(respResult), headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> respResult(int code, String msg) {
        respResult = new RespResult();
        respResult.setRespCode(code);
        respResult.setRespMsg(msg);
        log.info("返回数据为：【{}】",respResult);
        setCrossDomain();
        return new ResponseEntity<>(JSON.toJSONString(respResult), headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> respResultNoCD(int code, String msg) {
        respResult = new RespResult();
        respResult.setRespCode(code);
        respResult.setRespMsg(msg);
        log.info("返回数据为：【{}】",respResult);
        return new ResponseEntity<>(JSON.toJSONString(respResult), null, HttpStatus.OK);
    }

    public static ResponseEntity<String> respResultDataSUCCEED(Object data) {
        return CommonUtil.respResult(RespCode.MSG_SUCCEED.toValue(), data);
    }

    public static ResponseEntity<String> respResultCXYC() {
        return CommonUtil.respResult(RespCode.MSG_CSYC.toValue(), RespCode.MSG_CSYC.toMsg());
    }

    public static ResponseEntity<String> respResultXGCG() {
        return CommonUtil.respResult(RespCode.MSG_XGCG.toValue(), RespCode.MSG_XGCG.toMsg());
    }

    public static ResponseEntity<String> respResultXGSB() {
        return CommonUtil.respResult(RespCode.MSG_XGSB.toValue(), RespCode.MSG_XGSB.toMsg());
    }

    public static ResponseEntity<String> respResultTJCG() {
        return CommonUtil.respResult(RespCode.MSG_TJCG.toValue(), RespCode.MSG_TJCG.toMsg());
    }

    public static ResponseEntity<String> respResultTJSB() {
        return CommonUtil.respResult(RespCode.MSG_TJSB.toValue(), RespCode.MSG_TJSB.toMsg());
    }

    public static ResponseEntity<String> respResultSCCG() {
        return CommonUtil.respResult(RespCode.MSG_SCCG.toValue(), RespCode.MSG_SCCG.toMsg());
    }

    public static ResponseEntity<String> respResultSCSB() {
        return CommonUtil.respResult(RespCode.MSG_SCSB.toValue(), RespCode.MSG_SCSB.toMsg());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> T getMapTo(Map<String, Object> map, String key, Class<T> clas) {
        T t = null;
        if (map.containsKey(key)) {
            try {
                t = (T) map.get(key);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error(e.getLocalizedMessage());
                t = null;
            }
        }
        return t;
    }

    public static void disposeNull(Map<String, Object> map) {
        for (Entry<String, Object> entry : map.entrySet()) {
            Object object = entry.getValue();
            if (!isNull(object) || "".equals(object)) {
                throw new IllegalArgumentException(entry.getKey() + " 不能为空！");
            }
        }
    }

    public static void disposeListNull(List<Map<String, Object>> map) {
        for (Map<String, Object> lement : map) {
            disposeNull(lement);
        }
    }

    public static boolean disposeNull(Map<String, Object> map, List<String> keyNull) {
        for (Entry<String, Object> entry : map.entrySet()) {
            if (keyNull.contains(entry.getKey())) {
                continue;
            }
            Object object = entry.getValue();
            if (!isNull(object) || "".equals(object)) {
                return false;
            }
        }
        return true;
    }

    public static void initRedis(RedisTemplate<String, Object> redisTemplate,
                                 RedisTemplate<String, Object> redisTemplate1) {
        RedisUtil.setRedisTemplate(redisTemplate, redisTemplate1);
    }

    public static Map<String, Object> initService(RedisTemplate<String, Object> redisTemplate,
                                                  RedisTemplate<String, Object> redisTemplate1) throws Exception {
        initRedis(redisTemplate, redisTemplate1);
        Map<String, Object> map = setKey();
        map.put("link", Base64Util.base64Encode(Constants.SERVICE_URL.getBytes()));
        return map;
    }

    public static String getRsaData(String hysbptk) throws Exception {
        Map<String, Object> aesData = CommonUtil.getTokenMap();
        String prk = String.valueOf(aesData.get("prk"));
        String puk = String.valueOf(aesData.get("puk"));
        byte[] ba = Base64Util.base64Decode(hysbptk);
        String sign = RSAUtil.sign(ba, prk);
        boolean boo = RSAUtil.verify(ba, puk, sign);
        if (!boo) {
            return "";
        }
        byte[] data = RSAUtil.decryptByPrivateKey(ba, prk);
        return new String(data);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getTokenMap() {
        return (Map<String, Object>) REDISUTIL.hashGet(REDISUTIL.getRedisTemplate1(), getSessionId(),
                Constants.TOKEN_KEY);
    }

    public static Map<String, Object> setKey() throws Exception {
        String key = UUID.randomUUID().toString();
        Map<String, Object> rsaKey = null;
        rsaKey = RSAUtil.initKey();
        Long times = DateUtil.getSecond() + Constants.TOKEN_CACHE_TIMES;
        rsaKey.put("ptk", key.substring(0, 16));
        rsaKey.put("time", times);
        try {
            REDISUTIL.putHash(REDISUTIL.getRedisTemplate1(), getSessionId(), Constants.TOKEN_KEY, rsaKey);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Constants.TOKEN_CACHE.put(key, times);
            log.error(e.getLocalizedMessage());
        }
        rsaKey.remove("prk");
        rsaKey.put("ptk", Base64Util.base64Encode(key.getBytes()));
        return rsaKey;
    }

    public static boolean checkToken(String key) {
        if (Constants.TOKEN_CACHE.containsKey(key)) {
            if (DateUtil.getSecond() >= Constants.TOKEN_CACHE.get(key)) {
                Constants.TOKEN_CACHE.remove(key);
                return false;
            }
        } else {
            Map<String, Object> map = getTokenMap();
            String keys = String.valueOf(map.get("ptk"));
            Long times = (Long) map.get("time");
            if (key.equals(keys) && DateUtil.getSecond() >= times) {
                return false;
            }
        }
        return true;
    }

    public static boolean filterPublic(String me) {
        boolean is = !"hysbptk".equals(me) && !"hysbptkA".equals(me) && !"getverifyImUrl".equals(me)
                && !"getSMS".equals(me) && !"smsLogin".equals(me) && !"smspaLogin".equals(me)
                && !"getverifyImStream".equals("me") && !"getverifyImBase64".equals(me) && !"hysbptkS".equals(me)
                && !"hysbptkX".equals(me) && !"exportToExcel".equals(me) && !"importToExcel".equals(me);
        return is;
    }

    public static Object mapToBean(Map<String, Object> map, Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(object, value);
                }
            }
        } catch (Exception e) {
            log.info(e.getLocalizedMessage());
        }
        return object;
    }
}
