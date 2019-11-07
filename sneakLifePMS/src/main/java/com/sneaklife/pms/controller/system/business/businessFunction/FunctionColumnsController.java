package com.sneaklife.pms.controller.system.business.businessFunction;

import com.sneaklife.pms.service.system.business.businessFunction.FunctionColumnsService;
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
public class FunctionColumnsController {

    @Autowired
    private FunctionColumnsService functionColumnsService;

    @RequestMapping(value = "/functionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionColumns(HttpServletRequest request) throws Exception{
        return functionColumnsService.functionColumns(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/functionColumnsTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> functionColumnsTableView(HttpServletRequest request) throws Exception{
        return functionColumnsService.functionColumnsTableView(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getFunctionColumns(HttpServletRequest request) throws Exception{
        return functionColumnsService.getFunctionColumns(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/insertFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertFunctionColumns(HttpServletRequest request) throws Exception{
        functionColumnsService.insertFunctionColumns(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateFunctionColumns(HttpServletRequest request) throws Exception{
        functionColumnsService.updateFunctionColumns(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteFunctionColumns", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteFunctionColumns(HttpServletRequest request) throws Exception{
        functionColumnsService.deleteFunctionColumns(CommonUtil.getData(request));
    }
}
