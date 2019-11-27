package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 暂时废弃
@Controller
public class FunctionConfigController {

    @Autowired
    private FunctionConfigService functionConfigService;

    @RequestMapping(value = "/functionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionConfig() throws Exception{
        return functionConfigService.functionConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/functionConfigTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionConfigTreeView() throws Exception{
        return functionConfigService.functionConfigTreeView(IwsContext.getData());
    }

    @RequestMapping(value = "/getFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionConfig() throws Exception{
        return functionConfigService.getFunctionConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/insertFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionConfig() throws Exception{
        functionConfigService.insertFunctionConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionConfig() throws Exception{
        functionConfigService.updateFunctionConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionConfig() throws Exception{
        functionConfigService.deleteFunctionConfig(IwsContext.getData());
    }
}
