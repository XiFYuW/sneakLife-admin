package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.UserRoleService;
import com.sneaklife.util.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/userRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> userRole(HttpServletRequest request) throws Exception{
        return userRoleService.userRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getUserRole(HttpServletRequest request) throws Exception{
        return userRoleService.getUserRole(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/insertUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertUserRole(HttpServletRequest request) throws Exception{
        userRoleService.insertUserRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateUserRole(HttpServletRequest request) throws Exception{
        userRoleService.updateUserRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteUserRole(HttpServletRequest request) throws Exception{
        userRoleService.deleteUserRole(CommonUtil.getData(request));
    }
}
