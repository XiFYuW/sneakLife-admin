package com.sneaklife.pms.controller;

import com.alibaba.fastjson.JSON;
import com.sneaklife.pms.service.check.SneakLifeCheckService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.keyless.KeyLessContext;
import com.sneaklife.ut.page.PageInfo;
import com.sneaklife.ut.iws.ReqParam;
import com.sneaklife.ut.iws.RespCode;
import com.sneaklife.ut.servlet.SneakLifeServlet;
import com.sneaklife.ut.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
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
@SuppressWarnings("unchecked")
@Slf4j
public class ServiceController {

    private final HashOperations hashOperations;

    private final SneakLifeCheckService sneakLifeCheckService;

    @Autowired
    public ServiceController(HashOperations hashOperations, SneakLifeCheckService sneakLifeCheckService) {
        this.hashOperations = hashOperations;
        this.sneakLifeCheckService = sneakLifeCheckService;
    }

    @RequestMapping(value = "/service", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/plain;charset=UTF-8")
    public ModelAndView service(@RequestParam String data, HttpServletRequest request, HttpServletResponse response) throws Exception{
        data = IwsContext.getRequestData(request, response, hashOperations, data);
        ReqParam reqParam = Objects.requireNonNull(JSON.parseObject(data, ReqParam.class));
        String me = reqParam.getMe();
        String cs = StringUtil.filterDangerString(reqParam.getData());
        String pag = reqParam.getPag();
        String checkInId = reqParam.getCheckInId();
        Map<String, Object> pa = new HashMap<>();
        pa.put("data", cs);
        if (!StringUtils.isEmpty(pag)) {
            PageInfo pageInfo = JSON.parseObject(pag, PageInfo.class);
            pa.put("pag", pageInfo);
        }
        if (!StringUtils.isEmpty(checkInId)) {
            sneakLifeCheckService.checkIn((Map<String,Object>)JSON.parse(cs), checkInId);
        }
        log.info("前台请求参数: 【{}】", pa);
        return new ModelAndView("forward:/" + me, pa);
    }

    @RequestMapping(value = "/heartBeat", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> common(@RequestParam(required = false) String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SneakLifeServlet sneakLifeServlet = IwsContext.getSneakLifeServletObject(request,response);
        Map<String,Object> param = (Map<String,Object>)JSON.parse(data);
        sneakLifeCheckService.checkLogin(param);
        Map<String, Object> map = KeyLessContext.setKey(sneakLifeServlet.getCacheSessionId(), hashOperations);
        log.info("heartBeat接口返回数据: 【{}】", map);
        return IwsContext.respResultBody(RespCode.MSG_LOGIN_SUCCEED.toValue(), map);
    }
}
