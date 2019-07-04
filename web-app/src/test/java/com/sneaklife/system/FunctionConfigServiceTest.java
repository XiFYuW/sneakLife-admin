package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.service.system.authority.FunctionConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class FunctionConfigServiceTest {
    @Autowired
    private FunctionConfigService functionConfigService;

    @Test
    public void functionConfig(){
        functionConfigService.functionConfig(null);
    }

    /**
     * map.put("menuId","b8209ee58b3411e99d7380fa5b3a283a");
     * map.put("treeViewId","99a6c4b38b4711e99d7380fa5b3a283a");
     */
    @Test
    public void getFunctionConfig(){
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","99a6c4b38b4711e99d7380fa5b3a283a");
        map.put("treeViewId","b8209ee58b3411e99d7380fa5b3a283a");
        map.put("name","DataDictionary");
        functionConfigService.getFunctionConfig(map);
    }
}
