package com.sneaklife.pms.controller.system;

import com.sneaklife.service.system.SystemMenuService;
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
        return systemMenuService.getMenu();
    }
}
