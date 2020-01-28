package com.sneaklife.pms.controller.system.menu;

import com.sneaklife.pms.service.system.menu.SystemMenuService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemMenuController {

    private final SystemMenuService systemMenuService;

    @Autowired
    public SystemMenuController(SystemMenuService systemMenuService) {
        this.systemMenuService = systemMenuService;
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getMenu")
    public ResponseEntity<String> getMenu() throws Exception {
        return IwsContext.respResultBodyToSC(systemMenuService.getMenu());
    }

    @RequestMapping(value = "/systemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/systemFunctionMenu")
    public ResponseEntity<String> systemFunctionMenu() throws Exception{
        return IwsContext.respResultBodyToSC(systemMenuService.buildData(IwsContext.getData()));
    }

    @RequestMapping(value = "/getSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getSystemFunctionMenu")
    public ResponseEntity<String> getSystemFunctionMenu() throws Exception{
        return IwsContext.respResultBodyToSC(systemMenuService.getData(IwsContext.getData(),IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/insertSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertSystemFunctionMenu")
    public void insertSystemFunctionMenu() throws Exception{
        systemMenuService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateSystemFunctionMenu")
    public void updateSystemFunctionMenu() throws Exception{
        systemMenuService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteSystemFunctionMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteSystemFunctionMenu")
    public void deleteSystemFunctionMenu() throws Exception{
        systemMenuService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/selectTreeView", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/selectTreeView")
    public ResponseEntity<String> selectTreeView(){
        return IwsContext.respResultBodyToSC(systemMenuService.selectTreeView(IwsContext.getData()));
    }
}
