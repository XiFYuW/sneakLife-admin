package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionButtonService;
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
public class FunctionButtonController {

    private final FunctionButtonService functionButtonService;

    @Autowired
    public FunctionButtonController(FunctionButtonService functionButtonService) {
        this.functionButtonService = functionButtonService;
    }

    @RequestMapping(value = "/functionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionButton() {
        return IwsContext.respResultBodyToSC(functionButtonService.functionButton(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionButtonTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionButtonTableView() {
        return IwsContext.respResultBodyToSC(functionButtonService.functionButtonTableView(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionButton() throws Exception {
        return IwsContext.respResultBodyToSC(functionButtonService.getFunctionButton(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionButton() throws Exception {
        functionButtonService.insertFunctionButton(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionButton() throws Exception {
        functionButtonService.updateFunctionButton(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionButton() throws Exception {
        functionButtonService.deleteFunctionButton(IwsContext.getData());
    }
}
