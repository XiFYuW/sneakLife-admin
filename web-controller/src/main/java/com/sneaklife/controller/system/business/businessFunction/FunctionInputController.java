package com.sneaklife.controller.system.business.businessFunction;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.service.system.business.businessFunction.FunctionInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/22 11:44
 */
@Controller
public class FunctionInputController {

    @Autowired
    private FunctionInputService functionInputService;

    @RequestMapping(value = "/functionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInput(HttpServletRequest request) throws Exception{
        return functionInputService.functionInput(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/functionInputTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionInputTableView(HttpServletRequest request) throws Exception{
        return functionInputService.functionInputTableView(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionInput(HttpServletRequest request) throws Exception{
        return functionInputService.getFunctionInput(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/insertFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionInput(HttpServletRequest request) throws Exception{
        functionInputService.insertFunctionInput(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionInput(HttpServletRequest request) throws Exception{
        functionInputService.updateFunctionInput(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteFunctionInput", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionInput(HttpServletRequest request) throws Exception{
        functionInputService.deleteFunctionInput(CommonUtil.getData(request));
    }
}
