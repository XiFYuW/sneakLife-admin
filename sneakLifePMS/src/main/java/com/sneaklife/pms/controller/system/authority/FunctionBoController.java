package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionBoService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
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
    @AccessLogAn("/functionBo")
    public ResponseEntity<String> functionBo() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.functionBo(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionBoTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/functionBoTableView")
    public ResponseEntity<String> functionBoTableView() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getFunctionBo")
    public ResponseEntity<String> getFunctionBo() throws Exception{
        return IwsContext.respResultBodyToSC(functionBoService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertFunctionBo")
    public void insertFunctionBo() throws Exception{
        functionBoService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateFunctionBo")
    public void updateFunctionBo() throws Exception{
        functionBoService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionBo", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteFunctionBo")
    public void deleteFunctionBo() throws Exception{
        functionBoService.delete(IwsContext.getData());
    }
}
