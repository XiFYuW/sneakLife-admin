package com.sneaklife.ut.iws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.servlet.SneakLifeServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 15:53
 */
public class IwsContext {

    private static final Logger log = LoggerFactory.getLogger(IwsContext.class);

    private static ThreadLocal<SneakLifeServlet> sneakLifeServletLocal = new ThreadLocal<>();

    private static RespResult1 getRespResult(int code, String msg, Object data){
        boolean isMsg = "".equals(msg);
        boolean isData = null==data;
        if(isMsg && isData){
            return new RespResult1(code, RespCode.MSG_SUCCEED.toMsg(), new ArrayList<>(0));
        }
        if(!isMsg && isData){
            return new RespResult1(code, msg, new ArrayList<>(0));
        }
        if(isMsg && !isData){
            return new RespResult1(code, RespCode.MSG_SUCCEED.toMsg(), data);
        }
        return new RespResult1(code, msg, data);
    }

    public static ResponseEntity<String> respResultBody(int code, Object data) {
        RespResult1 respResult = getRespResult(code, "", data);
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        sneakLifeServlet.setCrossDomain();
        log.info("返回数据为：【{}】",respResult);
        return buildIwsBody(respResult, sneakLifeServlet);
    }

    public static ResponseEntity<String> respResultBody(int code, String msg) {
        RespResult1 respResult = getRespResult(code, msg, null);
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        sneakLifeServlet.setCrossDomain();
        log.info("返回数据为：【{}】",respResult);
        return buildIwsBody(respResult, sneakLifeServlet);
    }

    public static ResponseEntity<String> respResultBodyToSC(Object data) {
        return respResultBody(RespCode.MSG_SUCCEED.toValue(), data);
    }

    public static Map<String, Object> getData() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("data"));
            Map<String, Object> data = (Map<String, Object>) JSON.parse(object);
            log.info("提取前台请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    public static PageInfo getPageInfo() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("pag"));
            PageInfo data = JSON.parseObject(object, PageInfo.class);
            log.info("提取前台分页请求参数：【{}】", data);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    public static List<Map<String, Object>> getListData() {
        try {
            String object = String.valueOf(sneakLifeServletLocal.get().getHttpServletRequest().getAttribute("data"));
            List<Map<String, Object>> data = (List<Map<String, Object>>) JSON.parse(object);
            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
        }
        return null;
    }

    private static ResponseEntity<String> buildIwsBody(RespResult1 respResult,SneakLifeServlet sneakLifeServlet){
        return new ResponseEntity<>(JSON.toJSONString(respResult, SerializerFeature.DisableCircularReferenceDetect), sneakLifeServlet.getMvm(), HttpStatus.OK);
    }

    public static SneakLifeServlet getSneakLifeServletObject(HttpServletRequest request, HttpServletResponse response){
        SneakLifeServlet sneakLifeServlet = sneakLifeServletLocal.get();
        if(null == sneakLifeServlet){
            sneakLifeServlet = SneakLifeServletFactory.getSneakLifeServletObject(request,response);
            sneakLifeServletLocal.set(sneakLifeServlet);
        }
        return sneakLifeServlet;
    }
}
