package com.sneaklife.pms.service.system.monitoring;

import org.springframework.http.ResponseEntity;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/14 16:22
 */
public interface MonitoringService {

    void tomcatListen();

    void jvmListen();

    ResponseEntity<String> cpuListen() throws Exception;
}
