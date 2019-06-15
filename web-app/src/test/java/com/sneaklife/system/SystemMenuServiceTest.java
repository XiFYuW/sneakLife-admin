package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.dao.system.entity.SystemMenu;
import com.sneaklife.service.system.SystemMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class SystemMenuServiceTest {
    private static Logger log = LoggerFactory.getLogger(SystemMenuServiceTest.class);
    @Autowired
    private SystemMenuService systemMenuService;
    @Test
    public void getMenu(){
        List<SystemMenu> data = systemMenuService.getMenu();
        log.info("测试返回数据：{}", data);
    }
}
