package com.sneaklife.pms.controller.system.authority;

import com.sneaklife.pms.service.system.authority.UserRoleService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @RequestMapping(value = "/userRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/userRole")
    public ResponseEntity<String> userRole() throws Exception{
        return IwsContext.respResultBodyToSC(userRoleService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getUserRole")
    public ResponseEntity<String> getUserRole() throws Exception{
        return IwsContext.respResultBodyToSC(userRoleService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertUserRole")
    public void insertUserRole() throws Exception{
        userRoleService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateUserRole")
    public void updateUserRole() throws Exception{
        userRoleService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteUserRole")
    public void deleteUserRole() throws Exception{
        userRoleService.delete(IwsContext.getData());
    }
}
