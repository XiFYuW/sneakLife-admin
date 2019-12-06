package com.sneaklife.pms.controller.system.menu;

import com.sneaklife.pms.service.system.menu.SystemMenuService;
import com.sneaklife.ut.iws.IwsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @RequestMapping(value = "/getMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getMenu(){
        return IwsContext.respResultBodyToSC(systemMenuService.getMenu());
    }

    @RequestMapping(value = "/systemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> systemFunctionMenu() throws Exception{
        return IwsContext.respResultBodyToSC(systemMenuService.systemFunctionMenu(IwsContext.getData()));
    }

    @RequestMapping(value = "/getSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getSystemFunctionMenu() throws Exception{
        return IwsContext.respResultBodyToSC(systemMenuService.getSystemFunctionMenu(IwsContext.getData(),IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void insertSystemFunctionMenu() throws Exception{
        systemMenuService.insertSystemFunctionMenu(IwsContext.getData());
    }

    @RequestMapping(value = "/updateSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void updateSystemFunctionMenu() throws Exception{
        systemMenuService.updateSystemFunctionMenu(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public void deleteSystemFunctionMenu() throws Exception{
        systemMenuService.deleteSystemFunctionMenu(IwsContext.getData());
    }

    @RequestMapping(value = "/selectTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> selectTreeView(){
        return IwsContext.respResultBodyToSC(systemMenuService.selectTreeView(IwsContext.getData()));
    }
}
