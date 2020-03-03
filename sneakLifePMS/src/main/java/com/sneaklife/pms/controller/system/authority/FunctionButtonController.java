package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionButtonService;
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
public class FunctionButtonController {

    private final FunctionButtonService functionButtonService;

    @Autowired
    public FunctionButtonController(FunctionButtonService functionButtonService) {
        this.functionButtonService = functionButtonService;
    }

    @RequestMapping(value = "/functionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/functionButton")
    public ResponseEntity<String> functionButton() throws Exception {
        return IwsContext.respResultBodyToSC(functionButtonService.functionButton(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionButtonTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/functionButtonTableView")
    public ResponseEntity<String> functionButtonTableView() throws Exception{
        return IwsContext.respResultBodyToSC(functionButtonService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getFunctionButton")
    public ResponseEntity<String> getFunctionButton() throws Exception {
        return IwsContext.respResultBodyToSC(functionButtonService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertFunctionButton")
    public void insertFunctionButton() throws Exception {
        functionButtonService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateFunctionButton")
    public void updateFunctionButton() throws Exception {
        functionButtonService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteFunctionButton")
    public void deleteFunctionButton() throws Exception {
        functionButtonService.delete(IwsContext.getData());
    }
}
