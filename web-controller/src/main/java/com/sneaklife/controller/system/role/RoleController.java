package com.sneaklife.controller.system.role;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.service.system.dictionary.DataDictionaryService;
import com.sneaklife.service.system.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/insertRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertRole(HttpServletRequest request) throws Exception{
        roleService.insertRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateRole(HttpServletRequest request) throws Exception{
        roleService.updateRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteRole(HttpServletRequest request) throws Exception{
        roleService.deleteRole(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getRole(HttpServletRequest request) throws Exception{
        return roleService.getRole(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/roleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> role(HttpServletRequest request) throws Exception{
        return roleService.role(CommonUtil.getData(request));
    }
}
