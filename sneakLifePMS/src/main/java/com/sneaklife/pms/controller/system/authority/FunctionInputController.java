package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.FunctionInputService;
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
public class FunctionInputController {

    private final FunctionInputService functionInputService;

    @Autowired
    public FunctionInputController(FunctionInputService functionInputService) {
        this.functionInputService = functionInputService;
    }

    @RequestMapping(value = "/functionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInput() throws Exception{
        return IwsContext.respResultBodyToSC(functionInputService.functionInput(IwsContext.getData()));
    }

    @RequestMapping(value = "/functionInputTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInputTableView() throws Exception{
        return IwsContext.respResultBodyToSC(functionInputService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionInput() throws Exception{
        return IwsContext.respResultBodyToSC(functionInputService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionInput() throws Exception{
        functionInputService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionInput() throws Exception{
        functionInputService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionInput() throws Exception{
        functionInputService.delete(IwsContext.getData());
    }
}
