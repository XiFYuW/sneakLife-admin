package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleFunctionController {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @RequestMapping(value = "/roleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> roleFunction() throws Exception{
        return roleFunctionService.roleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/getRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getRoleFunction() throws Exception{
        return roleFunctionService.getRoleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/insertRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertRoleFunction() throws Exception{
        roleFunctionService.insertRoleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/updateRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateRoleFunction() throws Exception{
        roleFunctionService.updateRoleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteRoleFunction", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteRoleFunction() throws Exception{
        roleFunctionService.deleteRoleFunction(IwsContext.getData());
    }

    @RequestMapping(value = "/roleFunctionTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> buildRoleTreeView() throws Exception{
        return roleFunctionService.roleFunctionTreeView(IwsContext.getData());
    }

}