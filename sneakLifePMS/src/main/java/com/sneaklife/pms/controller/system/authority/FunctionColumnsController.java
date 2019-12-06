package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionColumnsService;
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
public class FunctionColumnsController {

    @Autowired
    private FunctionColumnsService functionColumnsService;

    @RequestMapping(value = "/functionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionColumns() {
        return IwsContext.respResultBodyToSC(functionColumnsService.functionColumns(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionColumnsTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionColumnsTableView() {
        return IwsContext.respResultBodyToSC(functionColumnsService.functionColumnsTableView(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionColumns() throws Exception {
        return IwsContext.respResultBodyToSC(functionColumnsService.getFunctionColumns(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionColumns() throws Exception {
        functionColumnsService.insertFunctionColumns(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionColumns() throws Exception {
        functionColumnsService.updateFunctionColumns(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionColumns() throws Exception {
        functionColumnsService.deleteFunctionColumns(IwsContext.getData());
    }
}
