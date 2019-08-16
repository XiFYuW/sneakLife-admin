package com.sneaklife.system;

import com.sneaklife.ClientApplication;
import com.sneaklife.service.system.authority.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/12 15:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@ContextConfiguration
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void updateUserRole() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("up", new ArrayList<>());
        userRoleService.updateUserRole(map);
    }
}
