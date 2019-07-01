package com.sneaklife.controller.system.authority;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.service.system.authority.FunctionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FunctionConfigController {

    @Autowired
    private FunctionConfigService functionConfigService;

    @RequestMapping(value = "/functionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionConfig(HttpServletRequest request) throws Exception{
        return functionConfigService.functionConfig(CommonUtil.getData(request));
    }
}
