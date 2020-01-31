package com.sneaklife.pms.controller.system.job;

import com.sneaklife.pms.service.system.job.SystemJobService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemJobController {

    private final SystemJobService systemJobService;

    @Autowired
    public SystemJobController(SystemJobService systemJobService) {
        this.systemJobService = systemJobService;
    }

    @RequestMapping(value = "/getSystemJob", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getSystemJob")
    public ResponseEntity<String> getSystemSql() throws Exception{
        return IwsContext.respResultBodyToSC(systemJobService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }
}
