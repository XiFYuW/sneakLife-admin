package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.service.system.authority.RoleFunctionService;
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
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class RoleFunctionServiceTest {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Test
    public void getRoleFunction(){
        Map<String,Object> map = new HashMap<>();
        roleFunctionService.getRoleFunction(map);
    }
}
