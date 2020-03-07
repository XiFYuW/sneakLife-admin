package com.sneaklife.ut.iws;

import com.alibaba.fastjson.JSON;
import com.sneaklife.config.pkv.CommonPKV;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.exception.SneakLifeFailureException;
import com.sneaklife.ut.keyless.AESUtil;
import com.sneaklife.ut.keyless.KeyLessContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.servlet.SneakLifeServletFactory;
import com.sneaklife.ut.spring.SpringContextUtil;
import com.sneaklife.ut.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 15:53
 */
@SuppressWarnings("unchecked")
@Slf4j
public class IwsContext {

    private static ThreadLocal<SneakLifeServlet> sneakLifeServletLocal = new ThreadLocal<>();

    private static final CommonPKV commonPKV = SpringContextUtil.getBean(CommonPKV.class);

    private static RespResult getRespResult(int code, String msg, Object data){
        boolean isMsg = "".equals(msg);
        boolean isData = null==data;
        if(isMsg && isData){
            return new RespResult(code, RespCode.MSG_SUCCEED.toMsg(), new ArrayList<>(0));
        }
        if(!isMsg && isData){
            return new RespResult(code, msg, new ArrayList<>(0));
        }
        if(isMsg && !isData){
            return new RespResult(code, RespCode.MSG_SUCCEED.toMsg(), data);
        }
        return new RespResult(code, msg, data);
    }

    public static ResponseEntity<String> respResultBody(int code, Object data) {
        RespResult respResult = getRespResult(code, "", data);
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        sneakLifeServlet.setCrossDomain();
        log.info("返回数据为：【{}】",respResult);
        return buildIwsBody(respResult, sneakLifeServlet);
    }

    public static ResponseEntity<String> respResultBody(int code, String msg) {
        RespResult respResult = getRespResult(code, msg, null);
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        sneakLifeServlet.setCrossDomain();
        log.info("返回数据为：【{}】",respResult);
        return buildIwsBody(respResult, sneakLifeServlet);
    }

    public static ResponseEntity<String> respResultBody(int code, String msg, Object data) {
        RespResult respResult = getRespResult(code, msg, data);
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        sneakLifeServlet.setCrossDomain();
        log.info("返回数据为：【{}】",respResult);
        return buildIwsBody(respResult, sneakLifeServlet);
    }

    public static ResponseEntity<String> respResultBodyToSC(Object data) {
        return respResultBody(RespCode.MSG_SUCCEED.toValue(), data);
    }

    private static ResponseEntity<String> buildIwsBody(RespResult respResult, SneakLifeServlet sneakLifeServlet){
        return new ResponseEntity<>(JSON.toJSONString(respResult), sneakLifeServlet.getMvm(), HttpStatus.OK);
    }

    public static Map<String, Object> getData() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("data"));
            if(StringUtil.isEmpty(object)){
                return new HashMap<>(0);
            }
            Map<String, Object> data = (Map<String, Object>) JSON.parse(object);
            log.info("提取前台请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new HashMap<>(0);
    }

    public static PageInfo getPageInfo() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("pag"));
            PageInfo data = JSON.parseObject(object, PageInfo.class);
            log.info("提取前台分页请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String, Object>> getListData() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("data"));
            return (List<Map<String, Object>>) JSON.parse(object);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static SneakLifeServlet getSneakLifeServletObject(HttpServletRequest request, HttpServletResponse response){
        SneakLifeServlet sneakLifeServlet = SneakLifeServletFactory.getSneakLifeServletObject(request,response);
        sneakLifeServletLocal.set(sneakLifeServlet);
        return sneakLifeServlet;
    }

    public static SneakLifeServlet getSneakLifeServletObject(){
        return sneakLifeServletLocal.get();
    }

    public static ResponseEntity<String> respResultXGCG() {
        return respResultBody(RespCode.MSG_XGCG.toValue(), RespCode.MSG_XGCG.toMsg());
    }

    public static ResponseEntity<String> respResultXGSB() {
        return respResultBody(RespCode.MSG_XGSB.toValue(), RespCode.MSG_XGSB.toMsg());
    }

    public static ResponseEntity<String> respResultTJCG() {
        return respResultBody(RespCode.MSG_TJCG.toValue(), RespCode.MSG_TJCG.toMsg());
    }

    public static ResponseEntity<String> respResultTJSB() {
        return respResultBody(RespCode.MSG_TJSB.toValue(), RespCode.MSG_TJSB.toMsg());
    }

    public static ResponseEntity<String> respResultSCCG() {
        return respResultBody(RespCode.MSG_SCCG.toValue(), RespCode.MSG_SCCG.toMsg());
    }

    public static ResponseEntity<String> respResultSCSB() {
        return respResultBody(RespCode.MSG_SCSB.toValue(), RespCode.MSG_SCSB.toMsg());
    }

    public static boolean isNotNull(Object ob) {
        return Optional.ofNullable(ob).isPresent();
    }

    public static Object getResponseEntityData(ResponseEntity<String> responseEntity){
        String body = responseEntity.getBody();
        Map<String, Object> map = (Map<String, Object>) JSON.parse(body);
        return Objects.requireNonNull(map).get("respData");
    }

    private static String checkRequestMethod(HttpServletRequest request, String data){
        if("GET".equals(request.getMethod())) {
            Map map = JSON.parseObject(data, Map.class);
            data = String.valueOf(map.get("data"));
        }
        return data;
    }

    public static String getRequestData(HttpServletRequest request, HttpServletResponse response, HashOperations hashOperations, String data) throws Exception {
        data = checkRequestMethod(request, data);
        SneakLifeServlet sneakLifeServlet = getSneakLifeServletObject(request,response);
        String sessionId = sneakLifeServlet.getCacheSessionId();
        log.info("RSA加密data: 【{}】", data);
        data = KeyLessContext.getRsaData(sessionId, data, hashOperations);
        Map map = JSON.parseObject(data, Map.class);
        log.info("RSA解密data: 【{}】", map);
        String token = String.valueOf(map.get("token"));
        token = KeyLessContext.getRsaData(sessionId, token, hashOperations);
        log.info("RSA解密token: 【{}】", token);
        if ("".equals(token)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_SZQMJYBTG.toValue(), RespCode.MSG_SZQMJYBTG.toMsg()));
        }
        if (!KeyLessContext.checkToken(sessionId, token, hashOperations)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_GQTOKEN.toValue(), RespCode.MSG_GQTOKEN.toMsg(),
                    KeyLessContext.setKey(sessionId ,hashOperations)));
        }
        if(!hashOperations.hasKey(sessionId, commonPKV.getUserKey())){
            throw new SneakLifeFailureException(IwsContext.respResultBody(RespCode.MSG_LOGIN_OVERDUE.toValue(),RespCode.MSG_LOGIN_OVERDUE.toMsg()));
        }
        try {
            data = AESUtil.aesDecrypt((String) map.get("data"), token);
            log.info("AES解密data: 【{}】", data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_AES_JMSB.toValue(), RespCode.MSG_AES_JMSB.toMsg()));
        }
        return data;
    }
}
