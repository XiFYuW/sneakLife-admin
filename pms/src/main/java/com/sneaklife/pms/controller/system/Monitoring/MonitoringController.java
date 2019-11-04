package com.sneaklife.pms.controller.system.Monitoring;

import com.sneaklife.service.system.monitoring.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/16 11:23
 */
@Controller
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @RequestMapping(value = "/cpuListen", method = RequestMethod.POST, produces = "application/plain;charset=UTF-8")
    public ResponseEntity<String> cpuListen() throws Exception{
        return monitoringService.cpuListen();
    }
}
