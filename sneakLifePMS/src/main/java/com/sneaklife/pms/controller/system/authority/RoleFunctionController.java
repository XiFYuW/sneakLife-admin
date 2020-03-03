package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleFunctionController {

    private final RoleFunctionService roleFunctionService;

    @Autowired
    public RoleFunctionController(RoleFunctionService roleFunctionService) {
        this.roleFunctionService = roleFunctionService;
    }

    @RequestMapping(value = "/roleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/roleFunction")
    public ResponseEntity<String> roleFunction() {
        return IwsContext.respResultBodyToSC(roleFunctionService.roleFunction(IwsContext.getData()));
    }

    @RequestMapping(value = "/getRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getRoleFunction")
    public ResponseEntity<String> getRoleFunction() throws Exception {
        return IwsContext.respResultBodyToSC(roleFunctionService.getRoleFunction(IwsContext.getData()));
    }

    @RequestMapping(value = "/insertRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertRoleFunction")
    public void insertRoleFunction() throws Exception{
        roleFunctionService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateRoleFunction")
    public void updateRoleFunction() throws Exception{
        roleFunctionService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/updateSpRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateSpRoleFunction")
    public void updateSpRoleFunction() throws Exception{
        roleFunctionService.updateSpRoleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteRoleFunction")
    public void deleteRoleFunction() throws Exception{
        roleFunctionService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/roleFunctionTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/roleFunctionTreeView")
    public ResponseEntity<String> roleFunctionTreeView() throws Exception{
        return IwsContext.respResultBodyToSC(roleFunctionService.roleFunctionTreeView(IwsContext.getData()));
    }

    @RequestMapping(value = "/roleFunctionTreeTableView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/roleFunctionTreeTableView")
    public ResponseEntity<String> roleFunctionTreeTableView() throws Exception{
        return IwsContext.respResultBodyToSC(roleFunctionService.buildData(IwsContext.getData()));
    }

}
