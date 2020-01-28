package com.sneaklife.pms.controller.system.log;

import com.sneaklife.pms.service.system.log.LogicalLogService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogicalLogController {

    private final LogicalLogService logicalLogService;

    @Autowired
    public LogicalLogController(LogicalLogService logicalLogService) {
        this.logicalLogService = logicalLogService;
    }

    @RequestMapping(value = "/insertLogicalLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/insertLogicalLog")
    public void insertLogicalLog() throws Exception{
        logicalLogService.insert(IwsContext.getData());
    }

    @RequestMapping(value = "/updateLogicalLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/updateLogicalLog")
    public void updateLogicalLog() throws Exception{
        logicalLogService.update(IwsContext.getData());
    }

    @RequestMapping(value = "/deleteLogicalLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/deleteLogicalLog")
    public void deleteLogicalLog() throws Exception{
        logicalLogService.delete(IwsContext.getData());
    }

    @RequestMapping(value = "/getLogicalLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getLogicalLog")
    public ResponseEntity<String> getLogicalLog() throws Exception{
        return IwsContext.respResultBodyToSC(logicalLogService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }

    @RequestMapping(value = "/logicalLog", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/logicalLog")
    public ResponseEntity<String> logicalLog() throws Exception{
        return IwsContext.respResultBodyToSC(logicalLogService.buildData(IwsContext.getData()));
    }
}
