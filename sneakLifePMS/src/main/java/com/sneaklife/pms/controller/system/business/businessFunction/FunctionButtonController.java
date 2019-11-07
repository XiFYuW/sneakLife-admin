package com.sneaklife.pms.controller.system.business.businessFunction;

import com.sneaklife.pms.service.system.business.businessFunction.FunctionButtonService;
import com.sneaklife.ut.common.CommonUtil;
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
public class FunctionButtonController {

    @Autowired
    private FunctionButtonService functionButtonService;

    @RequestMapping(value = "/functionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionButton(HttpServletRequest request) throws Exception{
        return functionButtonService.functionButton(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/functionButtonTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionButtonTableView(HttpServletRequest request) throws Exception{
        return functionButtonService.functionButtonTableView(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionButton(HttpServletRequest request) throws Exception{
        return functionButtonService.getFunctionButton(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/insertFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionButton(HttpServletRequest request) throws Exception{
        functionButtonService.insertFunctionButton(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionButton(HttpServletRequest request) throws Exception{
        functionButtonService.updateFunctionButton(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteFunctionButton", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionButton(HttpServletRequest request) throws Exception{
        functionButtonService.deleteFunctionButton(CommonUtil.getData(request));
    }
}
