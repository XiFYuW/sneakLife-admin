package com.sneaklife.pms;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.pms.service.common.SelectTreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class FunctionConfigServiceTest {
    @Autowired
    private SelectTreeService leftSelectViewService;

    @Test
    public void functionConfig() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","b8209ee58b3411e99d7380fa5b3a283a");
        leftSelectViewService.selectTree(map);
    }

}
