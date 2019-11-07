package com.sneaklife.app.pms;

import com.sneaklife.pms.service.system.SystemMenuService;
import com.sneaklife.app.SneakLifeAdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class SystemMenuServiceTest {
    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceTest.class);
    @Autowired
    private SystemMenuService systemMenuService;
    @Test
    public void getMenu(){
        systemMenuService.getMenu();
    }
}
