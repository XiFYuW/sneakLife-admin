package com.sneaklife.pms.controller.system.log;

import com.sneaklife.pms.service.system.log.AccessLogService;
import com.sneaklife.pms.service.system.log.LogicalLogService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessLogController {

    private final AccessLogService accessLogService;

    @Autowired
    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @RequestMapping(value = "/insertAccessLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertAccessLog")
    public void insertAccessLog() throws Exception{
        accessLogService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateAccessLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateAccessLog")
    public void updateAccessLog() throws Exception{
        accessLogService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteAccessLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteAccessLog")
    public void deleteAccessLog() throws Exception{
        accessLogService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/getAccessLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getAccessLog")
    public ResponseEntity<String> getAccessLog() throws Exception{
        return IwsContext.respResultBodyToSC(accessLogService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/accessLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/accessLog")
    public ResponseEntity<String> accessLog() throws Exception{
        return IwsContext.respResultBodyToSC(accessLogService.buildData(IwsContext.getData()));
    }
}
