package com.sneaklife.pms.controller;

import com.alibaba.fastjson.JSON;
import com.sneaklife.ut.exception.SneakLifeException;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.keyless.KeyLessContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.iws.ReqParam;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.keyless.AESUtil;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class ServiceController {

    @Autowired
    private HashOperations hashOperations;

    private Logger log = LoggerFactory.getLogger(ServiceController.class);

    @RequestMapping(value = "/service", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ModelAndView service(@RequestParam String data, HttpServletRequest request, HttpServletResponse response) throws Exception{
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject(request,response);
        String sessionId = sneakLifeServlet.getSessionId();
        log.debug("RSA加密data: 【{}】", data);
        data = KeyLessContext.getRsaData(sessionId, data, hashOperations);
        Map map = JSON.parseObject(data, Map.class);
        log.info("RSA解密data: 【{}】", map);
        String token = KeyLessContext.getRsaData(sessionId, String.valueOf(map.get("token")), hashOperations);
        log.info("RSA解密token: 【{}】", token);
        if ("".equals(token)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_SZQMJYBTG.toValue(), RespCode.MSG_SZQMJYBTG.toMsg()));
        }
        if (!KeyLessContext.checkToken(sessionId, token, hashOperations)) {
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_GQTOKEN.toValue(), KeyLessContext.setKey(sessionId ,hashOperations)));
        }
        try {
            data = AESUtil.aesDecrypt((String) map.get("data"), token);
            log.info("AES解密data: 【{}】", data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getLocalizedMessage());
            throw new SneakLifeException(IwsContext.respResultBody(RespCode.MSG_JMSBZS.toValue(), RespCode.MSG_JMSBZS.toMsg()));
        }
        ReqParam reqParam = Objects.requireNonNull(JSON.parseObject(data, ReqParam.class));
        String me = reqParam.getMe();
        String cs = StringUtil.filterDangerString(reqParam.getData());
        String pag = reqParam.getPag();
        Map<String, Object> pa = new HashMap<>();
        pa.put("data", cs);
        if (!StringUtils.isEmpty(pag)) {
            PageInfo pageInfo = JSON.parseObject(pag, PageInfo.class);
            pa.put("pag", pageInfo);
        }
        log.info("前台请求参数: 【{}】", pa);
        return new ModelAndView("forward:/" + me, pa);
    }

    @RequestMapping(value = "/heartBeat", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> common(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject(request,response);
        Map<String, Object> map = KeyLessContext.setKey(sneakLifeServlet.getSessionId() ,hashOperations);
        log.info("common接口返回数据: 【{}】", map);
        return IwsContext.respResultBody(RespCode.MSG_SUCCEED.toValue(), map);
    }
}
