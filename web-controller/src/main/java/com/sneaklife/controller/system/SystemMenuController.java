package com.sneaklife.controller.system;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.dao.system.entity.SystemMenu;
import com.sneaklife.service.system.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;
    @RequestMapping(value = "/getMenu", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> getMenu(){
        List<SystemMenu> data = systemMenuService.getMenu();
        return CommonUtil.respResultDataSUCCEED(data);
    }
}
