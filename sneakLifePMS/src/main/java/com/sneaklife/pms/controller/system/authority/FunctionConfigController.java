package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.ut.common.CommonUtil;
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

    @RequestMapping(value = "/functionConfigTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionConfigTreeView(HttpServletRequest request) throws Exception{
        return functionConfigService.functionConfigTreeView(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionConfig(HttpServletRequest request) throws Exception{
        return functionConfigService.getFunctionConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/insertFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionConfig(HttpServletRequest request) throws Exception{
        functionConfigService.insertFunctionConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionConfig(HttpServletRequest request) throws Exception{
        functionConfigService.updateFunctionConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionConfig(HttpServletRequest request) throws Exception{
        functionConfigService.deleteFunctionConfig(CommonUtil.getData(request));
    }
}
