package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.RoleConfigService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleConfigController {

    private final RoleConfigService roleConfigService;

    @Autowired
    public RoleConfigController(RoleConfigService roleConfigService) {
        this.roleConfigService = roleConfigService;
    }

    @RequestMapping(value = "/insertRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertRoleConfig")
    public void insertRoleConfig() throws Exception{
        roleConfigService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateRoleConfig")
    public void updateRoleConfig() throws Exception{
        roleConfigService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteRolConfig",method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteRolConfig")
    public void deleteRoleConfig() throws Exception{
        roleConfigService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/getRoleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getRoleConfig")
    public ResponseEntity<String> getRoleConfig() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/roleConfig", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/roleConfig")
    public ResponseEntity<String> roleConfig() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/selectsList", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/selectsList")
    public ResponseEntity<String> selectsList() throws Exception{
        return IwsContext.respResultBodyToSC(roleConfigService.selectsList(IwsContext.getData()));
    }
}
