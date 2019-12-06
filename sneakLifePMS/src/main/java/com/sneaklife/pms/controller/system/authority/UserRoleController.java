package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.UserRoleService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/userRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> userRole() {
        return IwsContext.respResultBodyToSC(userRoleService.userRole(IwsContext.getData()));
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getUserRole() throws Exception{
        return IwsContext.respResultBodyToSC(userRoleService.getUserRole(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertUserRole() throws Exception{
        userRoleService.insertUserRole(IwsContext.getData());
    }

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateUserRole() throws Exception{
        userRoleService.updateUserRole(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteUserRole() throws Exception{
        userRoleService.deleteUserRole(IwsContext.getData());
    }
}
