package com.sneaklife.util.system;

import com.sneaklife.pms.service.system.authority.FunctionConfigService;
import com.sneaklife.util.SneakLifeAdminApplication;
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
    private FunctionConfigService functionConfigService;

    @Test
    public void functionConfig(){
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","b8209ee58b3411e99d7380fa5b3a283a");
        functionConfigService.functionConfig(map);
    }

    @Test
    public void getFunctionConfig(){
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","99a6c4b38b4711e99d7380fa5b3a283a");
        map.put("treeViewId","b8209ee58b3411e99d7380fa5b3a283a");
        map.put("name","DataDictionary");
        functionConfigService.getFunctionConfig(map);
    }
}
