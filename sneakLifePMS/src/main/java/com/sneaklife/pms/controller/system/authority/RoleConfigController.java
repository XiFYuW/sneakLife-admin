package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.ut.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleConfigController {

    @Autowired
    private RoleConfigService roleConfigService;

    @RequestMapping(value = "/insertRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertRoleConfig(HttpServletRequest request) throws Exception{
        roleConfigService.insertRoleConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/updateRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateRoleConfig(HttpServletRequest request) throws Exception{
        roleConfigService.updateRoleConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/deleteRolConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteRoleConfig(HttpServletRequest request) throws Exception{
        roleConfigService.deleteRoleConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/getRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getRoleConfig(HttpServletRequest request) throws Exception{
        return roleConfigService.getRoleConfig(CommonUtil.getData(request), CommonUtil.getPageInfo(request));
    }

    @RequestMapping(value = "/roleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> roleConfig(HttpServletRequest request) throws Exception{
        return roleConfigService.roleConfig(CommonUtil.getData(request));
    }

    @RequestMapping(value = "/selectsList", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> selectsList(HttpServletRequest request) throws Exception{
        return roleConfigService.selectsList(CommonUtil.getData(request));
    }
}
