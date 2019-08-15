package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.service.system.monitoring.MonitoringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/14 16:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class MonitoringServiceTest {

    @Autowired
    private MonitoringService monitoringService;

    @Test
    public void jvmListen() throws Exception{
        monitoringService.cpuListen();
    }
}
