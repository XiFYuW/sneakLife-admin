package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionColumnsService;
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
public class FunctionColumnsController {

    private final FunctionColumnsService functionColumnsService;

    @Autowired
    public FunctionColumnsController(FunctionColumnsService functionColumnsService) {
        this.functionColumnsService = functionColumnsService;
    }

    @RequestMapping(value = "/functionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/functionColumns")
    public ResponseEntity<String> functionColumns() {
        return IwsContext.respResultBodyToSC(functionColumnsService.functionColumns(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionColumnsTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/functionColumnsTableView")
    public ResponseEntity<String> functionColumnsTableView() throws Exception{
        return IwsContext.respResultBodyToSC(functionColumnsService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getFunctionColumns")
    public ResponseEntity<String> getFunctionColumns() throws Exception {
        return IwsContext.respResultBodyToSC(functionColumnsService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertFunctionColumns")
    public void insertFunctionColumns() throws Exception {
        functionColumnsService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateFunctionColumns")
    public void updateFunctionColumns() throws Exception {
        functionColumnsService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteFunctionColumns")
    public void deleteFunctionColumns() throws Exception {
        functionColumnsService.delete(IwsContext.getData());
    }
}
