package com.sneaklife.controller.system.authority;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.service.system.authority.FunctionConfigService;
import com.sneaklife.service.system.authority.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleFunctionController {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @RequestMapping(value = "/roleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> roleFunction(HttpServletRequest request) throws Exception{
        return roleFunctionService.roleFunction(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getRoleFunction(HttpServletRequest request) throws Exception{
        return roleFunctionService.getRoleFunction(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/insertRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertRoleFunction(HttpServletRequest request) throws Exception{
        roleFunctionService.insertRoleFunction(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateRoleFunction(HttpServletRequest request) throws Exception{
        roleFunctionService.updateRoleFunction(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteRoleFunction(HttpServletRequest request) throws Exception{
        roleFunctionService.deleteRoleFunction(CommonUtil.getData(request));
    }
}
