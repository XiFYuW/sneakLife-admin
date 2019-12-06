package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleConfigController {

    @Autowired
    private RoleConfigService roleConfigService;

    @RequestMapping(value = "/insertRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertRoleConfig() throws Exception{
        roleConfigService.insertRoleConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/updateRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateRoleConfig() throws Exception{
        roleConfigService.updateRoleConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteRolConfig",method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteRoleConfig() throws Exception{
        roleConfigService.deleteRoleConfig(IwsContext.getData());
    }

    @RequestMapping(value = "/getRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getRoleConfig() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.getRoleConfig(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/roleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> roleConfig() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.roleConfig(IwsContext.getData()));
    }

    @RequestMapping(value = "/selectsList", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> selectsList() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.selectsList(IwsContext.getData()));
    }
}
