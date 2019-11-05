package com.sneaklife.pms.controller;

import com.alibaba.fastjson.JSON;
import com.sneaklife.util.code.page.PageInfo;
import com.sneaklife.util.code.req.ReqParam;
import com.sneaklife.util.code.resp.RespCode;
import com.sneaklife.util.common.CommonUtil;
import com.sneaklife.util.keyless.AESUtil;
import com.sneaklife.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ServiceController {
    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    private Logger log = LoggerFactory.getLogger(ServiceController.class);
    @RequestMapping(value = "/service", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ModelAndView service(@RequestParam String data) {
        log.info("RSA加密data: 【{}】", data);
        if(CommonUtil.getRequest() == null || CommonUtil.getResponse() == null) {
            CommonUtil.initHttp(request, response);
            CommonUtil.initRedis(redisTemplate, redisTemplate);
        }
        String token = "";
        Map map = null;
        try {
            data = CommonUtil.getRsaData(data);
            map = JSON.parseObject(data, Map.class);
            log.info("RSA解密data: 【{}】", map);
            token = CommonUtil.getRsaData((String) map.get("token"));
            log.info("RSA解密token: 【{}】", token);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            log.error(e1.getLocalizedMessage());
        }
        if ("".equals(token)) {
            return new ModelAndView("forward:/" + "dsc");
        }
        if (!CommonUtil.checkToken(token)) {
            return new ModelAndView("forward:/" + "to");
        }
        try {
            data = AESUtil.aesDecrypt((String) map.get("data"));
            log.info("AES解密data: 【{}】", data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
            return new ModelAndView("forward:/" + "dpf");
        }
        ReqParam reqParam = JSON.parseObject(data, ReqParam.class);
        String me = reqParam.getMe();
//        if (CommonUtil.filterPublic(me)) {
//            String userId = CommonUtil.getSessionValue(Constants.USERS_ID_TEMP, String.class);
//            if (!CommonUtil.isNull(userId)) {
//                return new ModelAndView("forward:/" + "iu");
//            }
//        }
        String cx = StringUtil.filterDangerString(reqParam.getData());
        String pag = reqParam.getPag();
        Map<String, Object> pa = new HashMap<>();
        pa.put("data", cx);
        if (CommonUtil.isNull(pag)) {
            PageInfo pageInfo = JSON.parseObject(pag, PageInfo.class);
            pa.put("pag", pageInfo);
        }
        log.info("前台请求参数: 【{}】", pa);
        return new ModelAndView("forward:/" + me, pa);
    }

    @RequestMapping(value = "/common", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> common() throws Exception {
        if(CommonUtil.getRequest() == null || CommonUtil.getResponse() == null) {
            CommonUtil.initHttp(request, response);
        }
        Map<String, Object> map = CommonUtil.initService(redisTemplate, redisTemplate);
        log.info("common接口返回数据: 【{}】", map);
        return CommonUtil.respResult(RespCode.MSG_SUCCEED.toValue(), map);
    }
}
