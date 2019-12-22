package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionBoService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 11:44
 */
@Controller
public class FunctionBoController {

    private final FunctionBoService functionBoService;

    @Autowired
    public FunctionBoController(FunctionBoService functionBoService) {
        this.functionBoService = functionBoService;
    }

    @RequestMapping(value = "/functionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInput() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.functionBo(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionBoTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInputTableView() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.functionBoTableView(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionInput() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.getFunctionBo(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionInput() throws Exception{
        functionBoService.insertFunctionBo(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionInput() throws Exception{
        functionBoService.updateFunctionBo(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionInput() throws Exception{
        functionBoService.deleteFunctionBo(IwsContext.getData());
    }
}
