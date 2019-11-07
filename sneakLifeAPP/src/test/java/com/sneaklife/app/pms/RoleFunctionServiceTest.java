package com.sneaklife.app.pms;

import com.sneaklife.pms.service.system.authority.RoleFunctionService;
import com.sneaklife.app.SneakLifeAdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/7 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class RoleFunctionServiceTest {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Test
    public void getRoleFunction() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("menuId","5a76e6b5b66511e985a680fa5b3a283a");
        roleFunctionService.getRoleFunction(map);
    }
}
