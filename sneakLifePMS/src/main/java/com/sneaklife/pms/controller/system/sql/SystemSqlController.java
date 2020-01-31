package com.sneaklife.pms.controller.system.sql;

import com.sneaklife.pms.service.system.sql.SystemSqlService;
import com.sneaklife.ut.iws.IwsContext;
import com.sneaklife.ut.log.AccessLogAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemSqlController {

    private final SystemSqlService systemSqlService;

    @Autowired
    public SystemSqlController(SystemSqlService systemSqlService) {
        this.systemSqlService = systemSqlService;
    }

    @RequestMapping(value = "/getSystemSql", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    @AccessLogAn("/getSystemSql")
    public ResponseEntity<String> getSystemSql() throws Exception{
        return IwsContext.respResultBodyToSC(systemSqlService.getData(IwsContext.getData(), IwsContext.getPageInfo()));
    }
}
